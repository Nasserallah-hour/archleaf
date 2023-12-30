package tn.atch.acrh.ReglementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.ReglementService.model.Reglement;

@Repository
public interface ReglementRepository extends JpaRepository<Reglement, Long> {
}
