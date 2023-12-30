package tn.atch.acrh.DeviseService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.DeviseService.model.Devise;

import java.util.List;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {
    List<Devise> findByClientId(Long clientId);
}
