package estateagency.service;

import estateagency.dto.realtors.RealtorDto;
import estateagency.dto.realtors.NewRealtorForm;
import estateagency.service.exceptions.NotUniqueUserName;
import estateagency.service.exceptions.RemovingErrorException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RealtorsService {
    Page<RealtorDto> findAllRealtors(Pageable page);

    RealtorDto findRealtorById(Long id);

    void createRealtor(NewRealtorForm realtor);

    void removeRealtorById(Long id) throws RemovingErrorException;

    Page<RealtorDto> searchRealtors(String search, Pageable pageable);

    void updateRealtor(RealtorDto realtor) throws NotUniqueUserName;

    void changePassword(String password, Long id);
}
