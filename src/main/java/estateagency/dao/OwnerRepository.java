package estateagency.dao;

import estateagency.model.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>, JpaSpecificationExecutor<Owner> {

    List<Owner> findByLastNameContaining(String name);

    Page<Owner> findByLastNameContaining(Pageable pageable, String name);

    Page<Owner> findByPassportContaining(Pageable page, String number);
}
