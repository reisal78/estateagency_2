package estateagency.service;

import estateagency.dto.contracts.ContractDto;
import estateagency.dto.contracts.ContractSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractsSalesService {

    ContractDto getNewContract(Long flatId);

    ContractDto createContract(ContractDto form);

    ContractDto saveContract(ContractDto form);

    ContractDto getExistingContract(Long flatId);

    void deleteContract(Long flatId);

    ContractDto closeContract(Long id);

    Page<ContractDto> findByCriteria(ContractSearchDto search, Pageable pageable);

    ContractDto findById(Long id);

    ContractDto findByFlatId(Long flatId);

    void removeReserve();
}
