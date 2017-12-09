package estateagency.service;

import estateagency.dto.flats.FlatDto;
import estateagency.dto.owners.OwnerDto;
import estateagency.service.exceptions.RemovingErrorException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService {
    Page<OwnerDto> findAllOwners(Pageable page);

    OwnerDto findOwnerById(Long id);

    void saveOwner(OwnerDto owner);

    void removeOwnerById(Long id) throws RemovingErrorException;

    Page<OwnerDto> findOwnerByLastName(Pageable pageable, String name);

    Page<OwnerDto> findOwnerByPassportNumber(Pageable pageable, String name);

    Page<OwnerDto> findOwnerByCriteria(Pageable pageable, String criteria);

    List<FlatDto> findOwnerFlats(Long id);
}
