package tn.atch.acrh.DeviseService.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;
import java.util.Optional;

@Controller
public class MVCDeviseController {
    @Autowired
    private ProductService productService;

    @Autowired
    private DeviseService deviseService;
    @Autowired
    private ClientService clientService;

//    @GetMapping("/cart")
//    public String viewCart(Model model, Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Client client = clientService.getClientByEmail(userDetails.getUsername());
//        List<Product> products=new ArrayList<>();
//        Optional<Devise> foundDevise = deviseService.getLatestDevise(client.getId());
//        Devise devise;
//        devise = foundDevise.orElseGet(() -> Devise.builder().client(client).build());
//        products = devise.getProductToBePurchased();
//
//        model.addAttribute("devise", devise);
//        model.addAttribute("products", products);
//        model.addAttribute("total", devise.getTotalAmount());
//
//        return "cart";
//    }

//    @PostMapping("/addToDevise")
//    public String addToCart(@RequestParam Long productId, Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Client client = clientService.getClientByEmail(userDetails.getUsername());
//        Product product = productService.getProductById(productId);
//        Optional<Devise> foundDevise = deviseService.getLatestDevise(client.getId());
//        Devise devise;
//        devise = foundDevise.orElseGet(() -> deviseService.createDevise(Devise.builder().client(client).isExpired(false).productToBePurchased(new ArrayList<>()).build()));
//        if(Objects.isNull(devise.getProductToBePurchased())){
//            devise.setProductToBePurchased(new ArrayList<>());
//        }
//        devise.getProductToBePurchased().add(product);
//        product.setQuantity(product.getQuantity()-1);
//        devise.setTotalAmount(0);
//        devise.getProductToBePurchased().forEach(
//                product1 -> {
//                    devise.setTotalAmount(devise.getTotalAmount() + productService.getProductById(product1.getId()).getPrice());
//                });
//        deviseService.updateDevise(devise.getId(), devise);
//        return "redirect:/cart";
//    }

//    @PostMapping("/removeProduct")
//    public String removeProduct(@RequestParam Long productId,Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Client client = clientService.getClientByEmail(userDetails.getUsername());
//        Product product = productService.getProductById(productId);
//        Devise devise = deviseService.getLatestDevise(client.getId()).orElseThrow();
//        devise.getProductToBePurchased().remove(product);
//        product.setQuantity(product.getQuantity()+1);
//        devise.setTotalAmount(0);
//                devise.getProductToBePurchased().forEach(
//                product1 -> {
//                    devise.setTotalAmount(devise.getTotalAmount() + productService.getProductById(product1.getId()).getPrice());
//                });
//        deviseService.updateDevise(devise.getId(),devise);
//        return "redirect:/cart";
//    }
}
