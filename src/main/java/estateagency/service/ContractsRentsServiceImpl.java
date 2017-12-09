package estateagency.service;


import estateagency.dao.ContractRentRepository;
import estateagency.dao.FlatRepository;
import estateagency.dto.contracts.ContractDto;
import estateagency.dto.contracts.ContractSearchDto;
import estateagency.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

@Service
public class ContractsRentsServiceImpl implements ContractsRentsService {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private ContractRentRepository contractRentRepository;

    @Override
    @Transactional(readOnly = true)
    public ContractDto getNewContract(Long flatId) {
        Flat flat = flatRepository.findOne(flatId);
        ContractDto contractDto = new ContractDto();
        contractDto.setFlat(flat);
        contractDto.setOwner(flat.getOwner());
        contractDto.setRealtor(this.getRealtor());
        contractDto.setCurrentRealtorId(this.getRealtor().getId());
        return contractDto;
    }

    @Override
    @Transactional
    public ContractDto createContract(ContractDto form) {
        Flat flat = flatRepository.findOne(form.getFlatId());
        flat.setReserved(true);
        ContractRent contractRent = new ContractRent();
        contractRent.setFlat(flat);
        contractRent.setRealtor(this.getRealtor());
        contractRent.setCommission(form.getCommission());
        contractRent.setCreateDate(new Date());
        Double totalPrice =  (flat.getPrice() * form.getCommission() / 100);
        contractRent.setTotalPrice(new BigDecimal(totalPrice).setScale(2, RoundingMode.UP).doubleValue());
        Client client = new Client();
        client.setId(form.getClientId());
        contractRent.setClient(client);
        return convertContractToDto(contractRentRepository.save(contractRent));
    }

    @Override
    @Transactional
    public ContractDto saveContract(ContractDto form) {
        ContractRent contract = contractRentRepository.findOne(form.getId());
        contract.setCommission(form.getCommission());
        Double totalPrice = (contract.getFlat().getPrice() * form.getCommission() / 100);
        contract.setTotalPrice(new BigDecimal(totalPrice).setScale(2, RoundingMode.UP).doubleValue());
        Client client = new Client();
        client.setId(form.getClientId());
        contract.setClient(client);
        return convertContractToDto(contractRentRepository.save(contract));
    }

    @Override
    @Transactional
    public ContractDto getExistingContract(Long flatId) {
        Contract contract = this.contractRentRepository.findByFlatId(flatId);
        return convertContractToDto(contract);
    }

    @Override
    @Transactional
    public void deleteContract(Long flatId) {
        Flat flat = this.flatRepository.findOne(flatId);
        flat.setReserved(false);
        flat.setAvailable(true);
        this.flatRepository.save(flat);
        this.contractRentRepository.deleteByFlatId(flatId);
    }

    @Override
    @Transactional
    public ContractDto closeContract(Long id) {
        ContractRent contract = this.contractRentRepository.findOne(id);
        contract.getFlat().setReserved(false);
        contract.getFlat().setAvailable(false);
        contract.setCompleteDate(new Date());
        this.contractRentRepository.save(contract);
        return convertContractToDto(contract);
    }

    @Override
    @Transactional
    public Page<ContractDto> findByCriteria(ContractSearchDto search, Pageable pageable) {
        Specification<ContractRent> specification = null;
        Page<ContractRent> page = this.contractRentRepository.findAll(specification, pageable);
        return convertContractPageToDtoPage(page);
    }

    @Override
    @Transactional(readOnly = true)
    public ContractDto findById(Long id) {
        return convertContractToDto(contractRentRepository.findOne(id));
    }

    @Override
    @Transactional(readOnly = true)
    public ContractDto findByFlatId(Long flatId) {
        return convertContractToDto(contractRentRepository.findByFlatId(flatId));
    }

    @Override
    @Transactional
    public void removeReserve() {
        Specification<ContractRent> completeDateSpecification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.isNull(root.get("completeDate"));
        Date date = new Date(new Date().getTime() - (3 * (1000 * 60 * 60 * 24)));
        Specification<ContractRent> createDateSpecification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("createDate"), date);

        Specification<ContractRent> specification = where(completeDateSpecification).and(createDateSpecification);
        List<ContractRent> removedContracts = this.contractRentRepository.findAll(specification);

        for (ContractRent contractRent : removedContracts) {
            this.deleteContract(contractRent.getFlat().getId());
        }
    }

    private Page<ContractDto> convertContractPageToDtoPage(Page<ContractRent> page) {
        List<ContractDto> dtoList = new ArrayList<>();
        for (ContractRent contract : page.getContent()) {
            dtoList.add(convertContractToDto(contract));
        }

        return new PageImpl<ContractDto>(
                dtoList,
                new PageRequest(page.getNumber(), page.getSize()),
                page.getTotalElements()
        );
    }

    private ContractDto convertContractToDto(Contract contract) {
        ContractDto dto = new ContractDto();
        dto.setFlat(contract.getFlat());
        dto.setOwner(contract.getFlat().getOwner());
        dto.setRealtor(contract.getRealtor());
        dto.setClient(contract.getClient());
        dto.setCommission(contract.getCommission());
        dto.setId(contract.getId());
        dto.setCurrentRealtorId(this.getRealtor().getId());
        dto.setCreateDate(contract.getCreateDate());
        dto.setCompleteDate(contract.getCompleteDate());
        dto.setTotalPrice(contract.getTotalPrice());
        dto.setAvailable(contract.getFlat().isAvailable());
        return dto;
    }


    private Realtor getRealtor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getRealtor();
    }

}
