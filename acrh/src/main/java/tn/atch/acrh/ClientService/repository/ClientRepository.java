package tn.atch.acrh.ClientService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.FactureService.model.Facture;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> { }
