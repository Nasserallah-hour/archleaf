package tn.atch.acrh.ReglementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ReglementService.model.Reglement;

import java.util.List;

@Repository
public interface ReglementRepository extends JpaRepository<Reglement, Long> {
    List<Reglement> findByFactureClient(Client client);
}
