package tn.atch.acrh.DeviseService.service;

import org.springframework.stereotype.Service;
import tn.atch.acrh.DeviseService.model.Devise;

import java.util.List;
import java.util.Optional;

@Service
public interface DeviseService {
    List<Devise> getAllDevises();

    Devise getDeviseById(Long id);

    Devise createDevise(Devise devise);

    Devise updateDevise(Long id, Devise devise);

    void deleteDevise(Long id);

    List<Devise> getDevisesByClientId(Long clientId);

    Optional<Devise> getLatestDevise(Long clientId);
}

