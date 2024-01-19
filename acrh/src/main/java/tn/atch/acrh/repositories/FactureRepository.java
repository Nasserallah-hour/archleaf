package tn.atch.acrh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.models.Client;
import tn.atch.acrh.models.Facture;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClientId(Long clientId);

    List<Facture> findByClientAndReglementIsNotNull(Client client);

    List<Facture> findByClientAndReglementIsNull(Client client);
}
