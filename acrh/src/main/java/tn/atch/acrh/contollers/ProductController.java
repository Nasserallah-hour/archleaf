package tn.atch.acrh.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tn.atch.acrh.models.BuyProductsForm;
import tn.atch.acrh.models.Client;
import tn.atch.acrh.models.Product;
import tn.atch.acrh.models.ProductCategory;
import tn.atch.acrh.repositories.ClientRepository;
import tn.atch.acrh.repositories.ProductCategoryRepository;
import tn.atch.acrh.repositories.ProductRepository;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ClientRepository clientRepository;


    @GetMapping({"/","/products"})
    public String getAllProducts(Model model) {
        List<Product> products = productRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("products", products);
        BuyProductsForm buyProductsForm = new BuyProductsForm();
        model.addAttribute("buyProductsForm", buyProductsForm);
        return "produits";
    }
    @GetMapping("/products/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productCategoryRepository.findAll());
        return "createProduit";
    }
    @PostMapping("/products/create-product")
    public String createProduct(@ModelAttribute Product product) {
        productRepository.save(product);

        // Redirect to the product list page after successful creation
        return "redirect:/products";
    }


    // Mapping to display all product categories
    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", productCategoryRepository.findAll());
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
        productCategoryRepository.save(category);
        // Redirect to the category list page after successful creation
        return "redirect:/categories";
    }
}