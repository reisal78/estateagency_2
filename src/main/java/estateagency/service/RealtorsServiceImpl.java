package estateagency.service;


import estateagency.dao.*;
import estateagency.dto.realtors.NewRealtorForm;
import estateagency.dto.realtors.RealtorDto;
import estateagency.model.Realtor;
import estateagency.model.User;
import estateagency.model.UserAuthority;
import estateagency.service.exceptions.NotUniqueUserName;
import estateagency.service.exceptions.RemovingErrorException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RealtorsServiceImpl implements RealtorsService {


    private final RealtorRepository realtorRepository;
    private final UserRepository userRepository;
    private final UserAuthorityRepository authorityRepository;
    private final ContractSaleRepository contractSaleRepository;
    private final ContractRentRepository contractRentRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Autowired
    public RealtorsServiceImpl(RealtorRepository realtorRepository,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               ModelMapper modelMapper,
                               UserAuthorityRepository authorityRepository,
                               ContractSaleRepository contractSaleRepository,
                               ContractRentRepository contractRentRepository) {
        this.realtorRepository = realtorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.authorityRepository = authorityRepository;
        this.contractSaleRepository = contractSaleRepository;
        this.contractRentRepository = contractRentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RealtorDto> findAllRealtors(Pageable page) {
        Page<Realtor> realtorPage = realtorRepository.findAll(page);
        return new PageImpl<>(convertListRealtorsToListRealtorsDto(realtorPage.getContent()), page, realtorPage.getTotalElements());
    }

    private List<RealtorDto> convertListRealtorsToListRealtorsDto(List<Realtor> realtors) {
        List<RealtorDto> dtos = new ArrayList<>();
        for (Realtor realtor :realtors) {
            RealtorDto dto =  modelMapper.map(realtor, RealtorDto.class);
            dto.setEnabled(userRepository.findByRealtorId(realtor.getId()).isEnabled());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public RealtorDto findRealtorById(Long id) {
        User user = userRepository.findByRealtorId(id);
        RealtorDto dto = modelMapper.map(user.getRealtor(), RealtorDto.class);
        dto.setEnabled(user.isEnabled());
        dto.setUsername(user.getUsername());
        dto.setAuthority(authorityRepository.findByUser(user).get(0).getAuthority());
        return dto;
    }

    @Override
    @Transactional
    public void createRealtor(NewRealtorForm dto) {
        Realtor realtor = modelMapper.map(dto, Realtor.class);
        realtorRepository.save(realtor);
        User user = new User();
        user.setRealtor(realtor);
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(dto.isEnabled());
        userRepository.save(user);
        UserAuthority authority = new UserAuthority(dto.getAuthority(), user);
        authorityRepository.save(authority);
    }

    @Override
    @Transactional
    public void updateRealtor(RealtorDto dto) throws NotUniqueUserName {
        Realtor realtor = this.realtorRepository.findOne(dto.getId());
        realtor.setFullName(dto.getFullName());
        realtor.setPhone(dto.getPhone());
        User user = userRepository.findByRealtorId(realtor.getId());
        if (!user.getUsername().equals(dto.getUsername())) {
            if (null != userRepository.findByUsername(dto.getUsername())) {
                throw new NotUniqueUserName("Username " + dto.getUsername() + " already exists");
            }
            user.setUsername(dto.getUsername());
        }
        user.setEnabled(dto.isEnabled());
        List<UserAuthority> authorities = authorityRepository.findByUser(user);
        UserAuthority authority = authorities.get(0);
        authority.setAuthority(dto.getAuthority());
        authorityRepository.save(authorities);
    }

    @Override
    @Transactional
    public void changePassword(String password, Long id) {
        User user = userRepository.findByRealtorId(id);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeRealtorById(Long id) throws RemovingErrorException {
        if (!contractRentRepository.findByRealtorId(id).isEmpty()) {
            throw new RemovingErrorException("This realtor have rent contracts");
        }
        if (!contractSaleRepository.findByRealtorId(id).isEmpty()) {
            throw new RemovingErrorException("This realtor have sales contracts");
        }

        User user = userRepository.findByRealtorId(id);
        List<UserAuthority> userAuthorities = authorityRepository.findByUser(user);
        for (UserAuthority userAuthority : userAuthorities) {
            authorityRepository.delete(userAuthorities);
        }
        userRepository.delete(user);
        realtorRepository.delete(id);
    }


    /**
     * @param search
     * @param pageable
     * @return
     */
    @Override
    public Page<RealtorDto> searchRealtors(String search, Pageable pageable) {


        Specification<Realtor> specification = null;

        if (search != null && !search.isEmpty()) {

            specification = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("fullName")), "%" + search.toUpperCase() + "%");
        }
        return convertPageRealtorsToDtoPage(realtorRepository.findAll(specification, pageable));
    }

    private Page<RealtorDto> convertPageRealtorsToDtoPage(Page<Realtor> page) {
        List<RealtorDto> dtoList = convertListRealtorsToListRealtorsDto(page.getContent());
        return new PageImpl<>(
                dtoList,
                new PageRequest(page.getNumber(), page.getSize()),
                page.getTotalElements()
        );
    }
}
