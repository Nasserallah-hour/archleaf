package tn.atch.acrh.ClientService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ClientService.repository.ClientRepository;
import tn.atch.acrh.ClientService.service.ClientService;

import java.util.List;

@Component
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        try {
            return clientRepository.findById(id)
                    .orElseThrow(() -> new Exception("Client not found with id " + id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        getClientById(id);
        client.setId(id);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        Client existingClient = getClientById(id);
        clientRepository.delete(existingClient);
    }
}

