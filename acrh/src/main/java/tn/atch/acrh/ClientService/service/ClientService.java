package tn.atch.acrh.ClientService.service;

import org.springframework.stereotype.Service;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.FactureService.model.Facture;

import java.util.List;
import java.util.Map;

@Service
public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client createClient(Client client);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);

    double calculateTotalRevenue(Client client);

    Map<Integer, Double> calculateRevenueByYear(Client client);

    double calculateRemainingAmount(Client client);

    List<Facture> getPaidInvoices(Client client);

    List<Facture> getUnpaidInvoices(Client client);
}

