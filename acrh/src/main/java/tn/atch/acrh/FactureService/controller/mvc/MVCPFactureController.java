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
        List<Devise> deviseList = deviseService.getDevisesByClientId(client.getId());
        Devise devise=null;
        if (!deviseList.isEmpty()) {
            devise = deviseList.stream().filter(devise1 -> devise1.getIsExpired().equals(false)).findFirst().orElseThrow();
        }
        Facture facture = factureService.createFacture(Facture.builder().client(client).productPurchased(devise!=null?devise.getProductToBePurchased():new ArrayList<>() ).build());
        model.addAttribute("products", facture.getProductPurchased());
        return "checkout";
    }
}