package tn.atch.acrh.ClientService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.ClientService.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findUserByEmail(String email);
}
