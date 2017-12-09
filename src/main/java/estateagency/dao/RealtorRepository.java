package estateagency.dao;

import estateagency.model.Realtor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealtorRepository extends JpaRepository<Realtor, Long>, JpaSpecificationExecutor<Realtor> {

}
