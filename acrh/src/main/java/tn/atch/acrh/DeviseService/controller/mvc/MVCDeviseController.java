package tn.atch.acrh.DeviseService.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ClientService.service.ClientService;
import tn.atch.acrh.DeviseService.model.Devise;
import tn.atch.acrh.DeviseService.service.DeviseService;
import tn.atch.acrh.ProductService.model.Product;
import tn.atch.acrh.ProductService.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MVCDeviseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private DeviseService deviseService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/cart")
    public String viewCart(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Client client = clientService.getClientByEmail(userDetails.getUsername());
        List<Devise> deviseList = deviseService.getDevisesByClientId(client.getId());
        Devise devise=null;
        List<Product> products=new ArrayList<>();
        if (!deviseList.isEmpty()) {
            devise = deviseList.stream().filter(devise1 -> devise1.getIsExpired().equals(false)).findFirst().orElseThrow();
            products = devise.getProductToBePurchased();
        }
        model.addAttribute("devise", devise);
        model.addAttribute("products", products);
        model.addAttribute("total",devise!=null ?devise.getTotalAmount():0);

        return "cart";
    }

    @PostMapping("/addToDevise")
    public String addToCart(@RequestParam Long productId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Client client = clientService.getClientByEmail(userDetails.getUsername());
        Product product = productService.getProductById(productId);
        List<Devise> deviseList = deviseService.getDevisesByClientId(client.getId());
        Devise devise;
        if (!deviseList.isEmpty()) {
            devise = deviseList.stream().filter(devise1 -> devise1.getIsExpired().equals(false)).findFirst().orElseThrow();
        } else {
            devise = deviseService.createDevise(Devise.builder().client(client).build());
        }
        devise.getProductToBePurchased().add(product);
        product.setQuantity(product.getQuantity()-1);
        devise.setTotalAmount(0);
        devise.getProductToBePurchased().forEach(
                product1 -> {
                    devise.setTotalAmount(devise.getTotalAmount() + productService.getProductById(product1.getId()).getPrice());
                });
        deviseService.updateDevise(devise.getId(), devise);
        return "redirect:/cart";
    }

    @PostMapping("/removeProduct")
    public String removeProduct(@RequestParam Long productId,Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Client client = clientService.getClientByEmail(userDetails.getUsername());
        Product product = productService.getProductById(productId);
        List<Devise> deviseList = deviseService.getDevisesByClientId(client.getId());
        Devise devise;
        devise = deviseList.stream().filter(devise1 -> devise1.getIsExpired().equals(false)).findFirst().orElseThrow();
        devise.getProductToBePurchased().remove(product);
        product.setQuantity(product.getQuantity()+1);
        devise.setTotalAmount(0);
                devise.getProductToBePurchased().forEach(
                product1 -> {
                    devise.setTotalAmount(devise.getTotalAmount() + productService.getProductById(product1.getId()).getPrice());
                });
        deviseService.updateDevise(devise.getId(),devise);
        return "redirect:/cart";
    }
}
