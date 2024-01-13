package tn.atch.acrh.ProductService.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ClientService.service.ClientService;
import tn.atch.acrh.ProductService.model.Product;
import tn.atch.acrh.ProductService.model.ProductCategory;
import tn.atch.acrh.ProductService.service.ProductService;

import java.util.List;

@Controller
public class MVCProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;


    @GetMapping({"/","/products"})
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("clients", clientService.getAllClients()); // Add clients to the model
        model.addAttribute("products", products);
        return "produits";
    }
    @GetMapping("/products/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getAllProductCategories());
        return "createProduit";
    }
    @PostMapping("/products/create-product")
    public String createProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        // Redirect to the product list page after successful creation
        return "redirect:/products";
    }


    // Mapping to display all product categories
    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", productService.getAllProductCategories());
        return "categories";
    }

    // Mapping to display the category creation form
    @GetMapping("/categories/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new ProductCategory());
        return "createCategory";
    }

    // Mapping to handle the category creation form submission
    @PostMapping("/categories/create")
    public String createCategory(@ModelAttribute ProductCategory category) {
        productService.createProductCategory(category);
        // Redirect to the category list page after successful creation
        return "redirect:/categories";
    }
}