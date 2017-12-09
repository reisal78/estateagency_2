package estateagency.service;

import estateagency.dao.ContractRentRepository;
import estateagency.dao.ContractSaleRepository;
import estateagency.dao.FlatRepository;
import estateagency.dao.OwnerRepository;
import estateagency.dto.flats.FlatDto;
import estateagency.dto.owners.OwnerDto;
import estateagency.model.Owner;
import estateagency.service.exceptions.RemovingErrorException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.jpa.domain.Specifications.where;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final FlatRepository flatRepository;
    private final ContractRentRepository contractRentRepository;
    private final ContractSaleRepository contractSaleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository,
                            FlatRepository flatRepository,
                            ContractRentRepository contractRentRepository,
                            ContractSaleRepository contractSaleRepository,
                            ModelMapper modelMapper) {
        this.ownerRepository = ownerRepository;
        this.flatRepository = flatRepository;
        this.contractRentRepository = contractRentRepository;
        this.contractSaleRepository = contractSaleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OwnerDto> findAllOwners(Pageable page) {
        Page<Owner> ownerPage = ownerRepository.findAll(page);
        return new PageImpl<>(convertListOwnersToListOwnersDto(ownerPage.getContent()), page, ownerPage.getTotalElements());
    }

    private List<OwnerDto> convertListOwnersToListOwnersDto(List<Owner> owners) {
        Set<Owner> ownerLinkedHashSet = new LinkedHashSet<>(owners);
        List<Owner> ownerList = new ArrayList<>(ownerLinkedHashSet);
        Type t = new TypeToken<List<OwnerDto>>() {
        }.getType();
        return modelMapper.map(ownerList, t);
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerDto findOwnerById(Long id) {
        return modelMapper.map(ownerRepository.findOne(id), OwnerDto.class);
    }

    @Override
    @Transactional
    public void saveOwner(OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        ownerRepository.save(owner);
    }

    @Override
    @Transactional
    public void removeOwnerById(Long id) throws RemovingErrorException {
        if (!this.flatRepository.findByOwnerId(id).isEmpty()) {
            throw new RemovingErrorException("Owner have flats");
        }
        if (!this.contractSaleRepository.findByFlatOwnerId(id).isEmpty()){
            throw new RemovingErrorException("Owner have sale contracts");
        }
        if (!this.contractRentRepository.findByFlatOwnerId(id).isEmpty()){
            throw new RemovingErrorException("Owner have sale contracts");
        }
        ownerRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OwnerDto> findOwnerByLastName(Pageable pageable, String name) {
        Page<Owner> ownerPage = ownerRepository.findByLastNameContaining(pageable, name);
        return new PageImpl<>(convertListOwnersToListOwnersDto(ownerPage.getContent()), pageable, ownerPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OwnerDto> findOwnerByPassportNumber(Pageable pageable, String number) {
        Page<Owner> ownerPage = ownerRepository.findByPassportContaining(pageable, number);
        return new PageImpl<>(convertListOwnersToListOwnersDto(ownerPage.getContent()), pageable, ownerPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OwnerDto> findOwnerByCriteria(Pageable pageable, String criteria) {

        Specification<Owner> specification = null;

        if (criteria != null && !criteria.isEmpty()) {
            Specification<Owner> specLastName = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("lastName")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specLastName);

            Specification<Owner> specFirstName = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specFirstName);

            Specification<Owner> specSurName = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("surName")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specSurName);

            Specification<Owner> specPassport = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("passport")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specPassport);

            Specification<Owner> specPhone = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("phone")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specPhone);
        }

        Page<Owner> page = ownerRepository.findAll(specification, pageable);

        return new PageImpl<>(convertListOwnersToListOwnersDto(page.getContent()), pageable, page.getTotalElements());
    }

    @Override
    public List<FlatDto> findOwnerFlats(Long id) {
        return null;
    }
}
