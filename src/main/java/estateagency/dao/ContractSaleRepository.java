package estateagency.dao;

import estateagency.model.Contract;
import estateagency.model.ContractSale;
import estateagency.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface ContractSaleRepository extends JpaRepository<ContractSale, Long>, JpaSpecificationExecutor<ContractSale> {
    Contract findByFlatId(Long flatId);
    void deleteByFlatId(Long flatId);
    List<Contract> findByClientId(Long clientId);
    List<Contract> findByRealtorId(Long realtorId);
    List<Contract> findByFlatOwnerId(Long ownerId);
    List<Contract> findByCompleteDateAndCreateDate(Date completeDate, Date createDate);
}
