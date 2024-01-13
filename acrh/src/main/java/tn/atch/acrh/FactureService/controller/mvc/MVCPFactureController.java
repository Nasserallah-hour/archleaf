package tn.atch.acrh.FactureService.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import java.util.Map;
import java.util.Optional;

@Controller
public class MVCPFactureController {

    @Autowired
    private FactureService factureService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;

    @Autowired
    private DeviseService deviseService;

//    @GetMapping("/checkout")
//    public String checkoutFacture (Model model email) {
//        Optional<Devise> foundDevise = deviseService.getLatestDevise(client.getId());
//        Devise devise;
//        if (foundDevise.isEmpty() || foundDevise.get().getIsExpired()) {
//            devise= Devise.builder().client(client).build();
//        }
//        else{
//            devise=foundDevise.get();
//        }
//        List<Product> purchasedList = devise.getProductToBePurchased().stream().toList();
//        devise.setIsExpired(true);
//        deviseService.updateDevise(devise.getId(),devise);
//        Facture facture = factureService.createFacture(Facture.builder().client(client).productPurchased(purchasedList).build());
//        model.addAttribute("products", facture.getProductPurchased());
//        return "checkout";
//    }

    @PostMapping("buy-products")
    public String buyProducts(@RequestParam("selectedQuantities") List<Integer> selectedQuantities,
                              @RequestParam("selectedProducts") List<Long> selectedProducts,
                              @RequestParam("clientId") Long clientId) throws Exception {
        // Process the selected products and quantities here
        // You can use selectedQuantities, selectedProducts, and clientId to perform the necessary actions
        if(selectedProducts.size()!=selectedQuantities.size()){
            throw new Exception("selections conflict");
        }
        List<Product> products = selectedProducts.stream().map(itemId -> productService.getProductById(itemId)).toList();
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setQuantity(products.get(i).getQuantity()-selectedQuantities.get(i));
            productService.updateProduct(products.get(i).getId(),products.get(i));
        }
        Facture facture = Facture.builder().client(clientService.getClientById(clientId)).productPurchased(products).build();
        Long factureId = factureService.createFacture(facture).getId();
        System.out.println("Buy request received. Client ID: " + clientId);
        System.out.println("Selected Products: " + selectedProducts);
        System.out.println("Selected Quantities: " + selectedQuantities);

        // Redirect to a success page or any other appropriate action
        return "redirect:/factures/details/" + factureId;    }

    @GetMapping("/details/{factureId}")
    public String showFactureDetails(@PathVariable Long factureId, Model model) {
        Facture facture = factureService.getFactureById(factureId); // Retrieve Facture from the service
        model.addAttribute("facture", facture); // Add the facture to the model for Thymeleaf rendering
        return "factureDetails"; // Return the Thymeleaf template name
    }
}