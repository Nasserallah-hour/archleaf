package tn.atch.acrh.ProductService.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tn.atch.acrh.ProductService.model.Product;
import tn.atch.acrh.ProductService.service.ProductService;

import java.util.List;

@Controller
public class MVCProductController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/","/products"})
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "products";
    }
}