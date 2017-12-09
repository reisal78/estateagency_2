package estateagency.service;

import estateagency.dto.flats.FlatDto;
import estateagency.dto.flats.FlatSearchDto;
import estateagency.service.exceptions.RemovingErrorException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlatService {
    List<String > getDistrictList();
    Page<FlatDto> findFlatsByCriteria(FlatSearchDto form, Pageable pageable);
    FlatDto findById(Long id);
    FlatDto saveFlat(FlatDto flatDto);
    List<FlatDto> findByOwnerId(Long id);

    Page<FlatDto> findAll(Pageable pageable);

    List<FlatDto> findByClientId(Long id);

    void removeFlat(Long id) throws RemovingErrorException;
}
