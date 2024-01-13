package tn.atch.acrh.ClientService.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ClientService.service.ClientService;
import tn.atch.acrh.FactureService.model.Facture;

import java.util.List;
import java.util.Map;

@Controller
public class MVCClientController {

    @Autowired
    private ClientService clientService;
    @GetMapping("/clients")
    public String getAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
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
        clientService.createClient(client);
        return "redirect:/clients";
    }

    @GetMapping("clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }

    @GetMapping("clients/details/{id}")
    public String getClientDetails(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        double totalRevenue = clientService.calculateTotalRevenue(client);
        Map<Integer, Double> revenueByYear = clientService.calculateRevenueByYear(client);
        double remainingAmount = clientService.calculateRemainingAmount(client);
        List<Facture> paidInvoices = clientService.getPaidInvoices(client);
        List<Facture> unpaidInvoices = clientService.getUnpaidInvoices(client);

        model.addAttribute("paidInvoices", paidInvoices);
        model.addAttribute("unpaidInvoices", unpaidInvoices);
        model.addAttribute("remainingAmount", remainingAmount);
        model.addAttribute("revenueGlobally", totalRevenue);
        model.addAttribute("revenueByYear", revenueByYear);
        return "clientDetails";
    }
}
