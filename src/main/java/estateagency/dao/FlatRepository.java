package estateagency.dao;

import estateagency.model.Flat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FlatRepository extends JpaRepository<Flat, Long>, JpaSpecificationExecutor<Flat> {
    @Query("SELECT DISTINCT c.district FROM Flat c ORDER BY c.district ASC")
    List<String> getDistrictsList();
    List<Flat> findByOwnerId(Long id);

    Page<Flat> findByAvailable(boolean available, Pageable pageable);
}
