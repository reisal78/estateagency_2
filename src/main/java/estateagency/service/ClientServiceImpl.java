package estateagency.service;

import estateagency.dao.ClientRepository;
import estateagency.dao.ContractRentRepository;
import estateagency.dao.ContractSaleRepository;
import estateagency.dto.clients.ClientDto;
import estateagency.model.Client;
import estateagency.service.exceptions.RemovingErrorException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.jpa.domain.Specifications.where;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final ContractRentRepository contractRentRepository;
    private final ContractSaleRepository contractSaleRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper, ContractRentRepository contractRentRepository, ContractSaleRepository contractSaleRepository) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.contractRentRepository = contractRentRepository;
        this.contractSaleRepository = contractSaleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDto> findAllClients(Pageable page) {
        Page<Client> clientPage = clientRepository.findAll(page);
        return new PageImpl<>(convertListClientsToListClientsDto(clientPage.getContent()), page, clientPage.getTotalElements());
    }

    private List<ClientDto> convertListClientsToListClientsDto(List<Client> clients) {
        Set<Client> clientLinkedHashSet = new LinkedHashSet<>(clients);
        List<Client> clientList = new ArrayList<>(clientLinkedHashSet);
        Type t = new TypeToken<List<ClientDto>>() {
        }.getType();
        return modelMapper.map(clientList, t);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto findClientById(Long id) {
        return modelMapper.map(clientRepository.findOne(id), ClientDto.class);
    }

    @Override
    @Transactional
    public void saveClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void removeClientById(Long id) throws RemovingErrorException {
        if (!this.contractSaleRepository.findByClientId(id).isEmpty()){
            throw new RemovingErrorException("Client have sale contracts");
        }
        if (!this.contractRentRepository.findByClientId(id).isEmpty()){
            throw new RemovingErrorException("Client have sale contracts");
        }
        clientRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDto> findClientByLastName(Pageable pageable, String name) {
        Page<Client> clientPage = clientRepository.findByLastNameContaining(pageable, name);
        return new PageImpl<>(convertListClientsToListClientsDto(clientPage.getContent()), pageable, clientPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDto> findClientByPassportNumber(Pageable pageable, String number) {
        Page<Client> clientPage = clientRepository.findByPassportContaining(pageable, number);
        return new PageImpl<>(convertListClientsToListClientsDto(clientPage.getContent()), pageable, clientPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDto> findClientByCriteria(Pageable pageable, String criteria) {

        Specification<Client> specification = null;

        if (criteria != null && !criteria.isEmpty()) {
            Specification<Client> specLastName = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("lastName")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specLastName);

            Specification<Client> specFirstName = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specFirstName);

            Specification<Client> specSurName = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("surName")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specSurName);

            Specification<Client> specPassport = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("passport")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specPassport);

            Specification<Client> specPhone = (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("phone")), "%" + criteria.toUpperCase() + "%");
            specification = where(specification).or(specPhone);
        }

        Page<Client> page = clientRepository.findAll(specification, pageable);

        return new PageImpl<>(convertListClientsToListClientsDto(page.getContent()), pageable, page.getTotalElements());
    }
}
