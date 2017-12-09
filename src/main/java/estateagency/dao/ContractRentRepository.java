package estateagency.dao;

import estateagency.model.Contract;
import estateagency.model.ContractRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface ContractRentRepository extends JpaRepository<ContractRent, Long>, JpaSpecificationExecutor<ContractRent> {
    Contract findByFlatId(Long flatId);
    void deleteByFlatId(Long flatId);
    List<Contract> findByClientId(Long clientId);
    List<Contract> findByRealtorId(Long realtorId);
    List<Contract> findByFlatOwnerId(Long ownerId);
    List<Contract> findByCompleteDateAndCreateDate(Date completeDate, Date createDate);
}
