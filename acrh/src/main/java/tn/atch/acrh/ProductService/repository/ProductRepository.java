package tn.atch.acrh.ProductService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.ProductService.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
