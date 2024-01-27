package com.example.rest.rest.service.impl;

import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.Client;
import com.example.rest.rest.repository.DatabaseClientRepository;
import com.example.rest.rest.service.ClientService;
import com.example.rest.rest.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseClientService implements ClientService {

    private final DatabaseClientRepository databaseClientRepository;

    @Override
    public List<Client> findAll() {
        return databaseClientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return databaseClientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format(
                        "Client with ID {0} not found",id)));
    }

    @Override
    public Client save(Client client) {
        return databaseClientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client existedClient = findById(client.getId());
        BeanUtils.copyNonNullProperties(client,existedClient);
        return databaseClientRepository.save(client);
    }

    @Override
    public void deleteById(Long id) {
        databaseClientRepository.findById(id);
    }
}
