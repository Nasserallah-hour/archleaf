package tn.atch.acrh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.models.Client;
import tn.atch.acrh.models.Reglement;

import java.util.List;

@Repository
public interface ReglementRepository extends JpaRepository<Reglement, Long> {
    List<Reglement> findByFactureClient(Client client);
}
