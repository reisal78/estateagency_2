package estateagency.service;

import estateagency.dto.clients.ClientDto;
import estateagency.service.exceptions.RemovingErrorException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface ClientService {
    @Transactional(readOnly = true)
    Page<ClientDto> findAllClients(Pageable page);

    @Transactional(readOnly = true)
    ClientDto findClientById(Long id);

    @Transactional
    void saveClient(ClientDto clientDto);

    @Transactional
    void removeClientById(Long id) throws RemovingErrorException;

    @Transactional(readOnly = true)
    Page<ClientDto> findClientByLastName(Pageable pageable, String name);

    @Transactional(readOnly = true)
    Page<ClientDto> findClientByPassportNumber(Pageable pageable, String number);

    @Transactional(readOnly = true)
    Page<ClientDto> findClientByCriteria(Pageable pageable, String criteria);
}
