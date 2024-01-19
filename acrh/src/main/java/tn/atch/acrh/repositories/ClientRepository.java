package tn.atch.acrh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> { }
