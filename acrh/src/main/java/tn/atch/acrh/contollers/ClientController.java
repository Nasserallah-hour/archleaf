package tn.atch.acrh.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.atch.acrh.models.Client;
import tn.atch.acrh.models.Facture;
import tn.atch.acrh.models.Reglement;
import tn.atch.acrh.repositories.ClientRepository;
import tn.atch.acrh.repositories.FactureRepository;
import tn.atch.acrh.repositories.ReglementRepository;

import java.time.Instant;
import java.util.*;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ReglementRepository reglementRepository;

    @GetMapping("/clients")
    public String getAllClients(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("clients/create")
    public String showCreateClientForm(Model model) {
        model.addAttribute("client", new Client()); // Provide an empty client object to the form
        return "createClient"; // Assuming you have a Thymeleaf template for creating a client
    }

    @PostMapping("clients/create")
    public String createClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        delete(id);
        return "redirect:/clients";
    }

    @GetMapping("clients/details/{id}")
    public String getClientDetails(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        model.addAttribute("client", client);
        double totalRevenue = calculateTotalRevenue(client);
        Map<Integer, Double> revenueByYear = calculateRevenueByYear(client);
        double remainingAmount = calculateRemainingAmount(client);
        List<Facture> paidInvoices = getPaidInvoices(client);
        List<Facture> unpaidInvoices = getUnpaidInvoices(client);

        model.addAttribute("paidInvoices", paidInvoices);
        model.addAttribute("unpaidInvoices", unpaidInvoices);
        model.addAttribute("remainingAmount", remainingAmount);
        model.addAttribute("revenueGlobally", totalRevenue);
        model.addAttribute("revenueByYear", revenueByYear);
        return "clientDetails";
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

    public List<Facture> getUnpaidInvoices(Client client) {
        return factureRepository.findByClientAndReglementIsNull(client);
    }

    public List<Facture> getPaidInvoices(Client client) {
        return factureRepository.findByClientAndReglementIsNotNull(client);
    }

    public void delete(Long id) {
        Client existingClient = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(existingClient);
    }


}
