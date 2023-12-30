package tn.atch.acrh.ProductService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.atch.acrh.ProductService.model.Product;
import tn.atch.acrh.ProductService.model.ProductCategory;
import tn.atch.acrh.ProductService.service.ProductService;

import java.util.List;
//TODO remove all reduntant calls to product entity
@RestController
@RequestMapping("/api/products-categories")
public class ProductCategoryController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) {
        try {
            ProductCategory product = productService.getProductCategoryById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<ProductCategory> getProductCategories() {
 return productService.getAllProductCategories();
    }

    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory createdProductCategory = productService.createProductCategory(productCategory);
        return new ResponseEntity<>(createdProductCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategory productCategory) {
        try {
            ProductCategory updatedProductCategory = productService.updateProductCategory(id, productCategory);
            return new ResponseEntity<>(updatedProductCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //TODO Add delete ProductCategory
}

