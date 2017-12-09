package estateagency.dao;

import estateagency.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    List<Client> findByLastNameContaining(String name);

    Page<Client> findByLastNameContaining(Pageable pageable, String name);

    Page<Client> findByPassportContaining(Pageable page, String number);
}
