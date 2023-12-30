package tn.atch.acrh.FactureService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.FactureService.model.Facture;
import tn.atch.acrh.FactureService.model.FactureInputDTO;
import tn.atch.acrh.FactureService.service.FactureService;
import tn.atch.acrh.ProductService.model.Product;
import tn.atch.acrh.ReglementService.model.PaymentMethod;
import tn.atch.acrh.ReglementService.model.Reglement;
import tn.atch.acrh.ReglementService.service.ReglementService;

import java.util.List;


@RestController
@RequestMapping("/api/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;
    @Autowired
    ReglementService reglementService;

    @GetMapping("/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Long id) {
        try {
            Facture facture = factureService.getFactureById(id);
            return new ResponseEntity<>(facture, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Facture> getAllFactures() {
        return factureService.getAllFactures();
    }

    @PostMapping
    public ResponseEntity<Facture> createFacture(@RequestBody FactureInputDTO factureInputDTO) {
        Facture createdFacture = factureService.createFacture(Facture.builder().productPurchased(factureInputDTO.productPurchasedIds().stream().map(productId -> Product.builder().id(productId).build()).toList()).client(Client.builder().id(factureInputDTO.clientId()).build()).build());
        reglementService.create(Reglement.builder().facture(createdFacture).price(createdFacture.getAmount()).paymentMethod(PaymentMethod.valueOf(factureInputDTO.paymentMethod())).build());
        return new ResponseEntity<>(createdFacture, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable Long id, @RequestBody Facture facture) {
        try {
            Facture updatedFacture = factureService.updateFacture(id, facture);
            return new ResponseEntity<>(updatedFacture, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        try {
            factureService.deleteFacture(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}")
    public List<Facture> getFacturesByClient(@PathVariable Long clientId) {
        return factureService.getFacturesByClientId(clientId);
    }
}