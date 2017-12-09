package estateagency.dao;

import estateagency.model.FlatImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlatImageRepository extends JpaRepository<FlatImage, Long> {
    List<FlatImage> findFlatImageByFlatId(Long flatId);
}
