package tn.atch.acrh.ProductService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.ProductService.model.Product;
import tn.atch.acrh.ProductService.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
