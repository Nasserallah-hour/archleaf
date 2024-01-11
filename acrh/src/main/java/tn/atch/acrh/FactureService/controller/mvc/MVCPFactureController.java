package tn.atch.acrh.FactureService.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ClientService.service.ClientService;
import tn.atch.acrh.DeviseService.model.Devise;
import tn.atch.acrh.DeviseService.service.DeviseService;
import tn.atch.acrh.FactureService.model.Facture;
import tn.atch.acrh.FactureService.service.FactureService;
import tn.atch.acrh.ProductService.model.Product;
import tn.atch.acrh.ProductService.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MVCPFactureController {

    @Autowired
    private FactureService factureService;
    @Autowired
    private ClientService clientService;

    @Autowired
    private DeviseService deviseService;

    @GetMapping("/checkout")
    public String checkoutFacture (Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Client client = clientService.getClientByEmail(userDetails.getUsername());
        Optional<Devise> foundDevise = deviseService.getLatestDevise(client.getId());
        Devise devise;
        if (foundDevise.isEmpty() || foundDevise.get().getIsExpired()) {
            devise= Devise.builder().client(client).build();
        }
        else{
            devise=foundDevise.get();
        }
        List<Product> purchasedList = devise.getProductToBePurchased().stream().toList();
        devise.setIsExpired(true);
        deviseService.updateDevise(devise.getId(),devise);
        Facture facture = factureService.createFacture(Facture.builder().client(client).productPurchased(purchasedList).build());
        model.addAttribute("products", facture.getProductPurchased());
        return "checkout";
    }
}