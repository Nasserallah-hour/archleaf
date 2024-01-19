package tn.atch.acrh.contollers;

import com.fasterxml.jackson.databind.deser.UnresolvedId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.atch.acrh.models.BuyProductsForm;
import tn.atch.acrh.models.Facture;
import tn.atch.acrh.models.Product;
import tn.atch.acrh.repositories.ClientRepository;
import tn.atch.acrh.repositories.FactureRepository;
import tn.atch.acrh.repositories.ProductRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
public class FactureController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private ProductRepository productRepository;

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
    public String buyProducts(@ModelAttribute("buyProductsForm") BuyProductsForm buyProductsForm) throws Exception {
         if(buyProductsForm.getSelectedQuantities().size()!=buyProductsForm.getSelectedQuantities().size()){
            throw new Exception("selections conflict");
        }
        List<Product> products = buyProductsForm.getSelectedQuantities().stream().map(itemId -> productRepository.findById(Long.valueOf(itemId)).orElseThrow()).toList();
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setQuantity(products.get(i).getQuantity()-buyProductsForm.getSelectedQuantities().get(i));
            productRepository.save(products.get(i));
        }
        Facture facture = Facture.builder().client(clientRepository.findById(buyProductsForm.getSelectedClient()).orElseThrow()).productPurchased(products).build();
        Long factureId = createFacture(facture).getId();

        return "redirect:/factures/details/" + factureId;    }

    public Facture createFacture(Facture facture) {
        facture.getProductPurchased().forEach(
                product -> {
                    facture.setAmount(facture.getAmount()+productRepository.findById(product.getId()).orElseThrow().getPrice());
                });
        return factureRepository.save(facture);
    }

    @GetMapping("/factures/details/{factureId}")
    public String showFactureDetails(@PathVariable Long factureId, Model model) {
        Facture facture = factureRepository.findById(factureId).orElseThrow();
        model.addAttribute("facture", facture);
    return "factureDetails";
    }

    @GetMapping("/factures")
    public String allFactures( Model model) {
        List<Facture> factures = factureRepository.findAll();
        model.addAttribute("factures", factures);
        return "factures";
    }
}