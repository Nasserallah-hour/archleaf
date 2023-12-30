package tn.atch.acrh.ClientService.service;

import org.springframework.stereotype.Service;
import tn.atch.acrh.ClientService.model.Client;

import java.util.List;

@Service
public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client createClient(Client client);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);
}

