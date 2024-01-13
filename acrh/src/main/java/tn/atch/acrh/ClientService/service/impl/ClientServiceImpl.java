package tn.atch.acrh.ClientService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ClientService.repository.ClientRepository;
import tn.atch.acrh.ClientService.service.ClientService;
import tn.atch.acrh.DeviseService.service.DeviseService;
import tn.atch.acrh.FactureService.model.Facture;
import tn.atch.acrh.FactureService.repository.FactureRepository;
import tn.atch.acrh.FactureService.service.FactureService;
import tn.atch.acrh.ReglementService.model.Reglement;
import tn.atch.acrh.ReglementService.repository.ReglementRepository;

import java.time.Instant;
import java.util.*;

@Component
public class ClientServiceImpl implements ClientService {
    @Autowired
    private DeviseService deviseService;

    @Autowired
    private FactureService factureService;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ReglementRepository reglementRepository;

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

    public double calculateTotalRevenue(Client client) {
        List<Facture> invoices = factureRepository.findByClientId(client.getId());
        return invoices.stream()
                .mapToDouble(Facture::getAmount)
                .sum();
    }

    public Map<Integer, Double> calculateRevenueByYear(Client client) {
        List<Facture> invoices = factureRepository.findByClientId(client.getId());
        Map<Integer, Double> revenueByYear = new HashMap<>();

        for (Facture invoice : invoices) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Date.from(Instant.from(invoice.getCreatedAt())));
            int year = calendar.get(Calendar.YEAR);

            double currentRevenue = revenueByYear.getOrDefault(year, 0.0);
            revenueByYear.put(year, currentRevenue + invoice.getAmount());
        }

        return revenueByYear;
    }

    public double calculateRemainingAmount(Client client) {
        List<Facture> invoices = factureRepository.findByClientId(client.getId());
        double totalAmountDue = invoices.stream()
                .mapToDouble(Facture::getAmount)
                .sum();

        List<Reglement> payments = reglementRepository.findByFactureClient(client);
        double totalAmountPaid = payments.stream()
                .mapToDouble(Reglement::getPrice)
                .sum();

        return totalAmountDue - totalAmountPaid;
    }

    public List<Facture> getPaidInvoices(Client client) {
        return factureRepository.findByClientAndReglementIsNotNull(client);
    }

    public List<Facture> getUnpaidInvoices(Client client) {
        return factureRepository.findByClientAndReglementIsNull(client);
    }
}

