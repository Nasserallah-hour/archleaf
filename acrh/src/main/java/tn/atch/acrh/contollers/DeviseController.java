package tn.atch.acrh.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class DeviseController {
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private DeviseService deviseService;
//    @Autowired
//    private ClientService clientService;

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
