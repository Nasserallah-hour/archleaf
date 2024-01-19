package tn.atch.acrh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
