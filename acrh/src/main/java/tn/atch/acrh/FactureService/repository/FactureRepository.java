package tn.atch.acrh.FactureService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.FactureService.model.Facture;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClientId(Long clientId);

    List<Facture> findByClientAndReglementIsNotNull(Client client);

    List<Facture> findByClientAndReglementIsNull(Client client);
}
