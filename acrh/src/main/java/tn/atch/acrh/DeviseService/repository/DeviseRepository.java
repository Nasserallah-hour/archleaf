package tn.atch.acrh.DeviseService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.atch.acrh.DeviseService.model.Devise;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {
    List<Devise> findByClientId(Long clientId);

    Optional<Devise> findFirstByClientIdOrderById(@Param("clientId") Long clientId);
}
