package tn.atch.acrh.FactureService.service;

import org.springframework.stereotype.Service;
import tn.atch.acrh.FactureService.model.Facture;

import java.util.List;

@Service
public interface FactureService {
    List<Facture> getAllFactures();

    Facture getFactureById(Long id);

    Facture createFacture(Facture facture);

    Facture updateFacture(Long id, Facture facture);

    void deleteFacture(Long id);

    List<Facture> getFacturesByClientId(Long clientId);
}
