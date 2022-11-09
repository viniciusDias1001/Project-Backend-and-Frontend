package io.github.vinicius1001.catalog.service;


import io.github.vinicius1001.catalog.dto.ClientDTO;
import io.github.vinicius1001.catalog.entities.Client;
import io.github.vinicius1001.catalog.repository.ClientRepository;
import io.github.vinicius1001.catalog.service.exception.DataBaseException;
import io.github.vinicius1001.catalog.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
   ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map(Client -> new ClientDTO(Client));

    }

    @Transactional(readOnly = true)
    public Optional<ClientDTO> findById(Long id){
      Optional<Client> client =clientRepository.findById(id);
         Client client1  = client.orElseThrow(() -> new EntityNotFoundException("Client not found in DateBase"));
         return Optional.of(new ClientDTO(client1));

    }


    @Transactional
    public ClientDTO save(ClientDTO clientDTO){
        Client client = new Client();
        copyDtoToEntity(client,clientDTO);
        clientRepository.save(client);
        return new ClientDTO(client);
    }


    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO){
        Optional<Client> client = clientRepository.findById(id);
        Client client1 = client.orElseThrow(() -> new EntityNotFoundException("Client not found in DateBase"));
        copyDtoToEntity(client1,clientDTO);
        clientRepository.save(client1);
        return clientDTO;
    }


    @Transactional
    public void delete(Long id){
        clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return client;
        }).orElseThrow(() -> new DataBaseException("Integrity violation"));
    }





    public void copyDtoToEntity(Client client, ClientDTO clientDTO){
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setChildren(clientDTO.getChildren());
       client.setBithDate(clientDTO.getBithDate());
    }



}
