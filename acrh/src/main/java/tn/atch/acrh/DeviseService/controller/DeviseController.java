package tn.atch.acrh.DeviseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.atch.acrh.DeviseService.model.Devise;
import tn.atch.acrh.DeviseService.service.DeviseService;

import java.util.List;


@RestController
@RequestMapping("/api/devise")
public class DeviseController {

    @Autowired
    private DeviseService deviseService;

    @GetMapping("/{id}")
    public ResponseEntity<Devise> getDeviseById(@PathVariable Long id) {
        try {
            Devise devise = deviseService.getDeviseById(id);
            return new ResponseEntity<>(devise, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Devise> getAllDevises() {
        return deviseService.getAllDevises();
    }

    @PostMapping
    public ResponseEntity<Devise> createDevise(@RequestBody Devise devise) {
        Devise createdDevise = deviseService.createDevise(devise);
        return new ResponseEntity<>(createdDevise, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devise> updateDevise(@PathVariable Long id, @RequestBody Devise devise) {
        try {
            Devise updatedDevise = deviseService.updateDevise(id, devise);
            return new ResponseEntity<>(updatedDevise, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevise(@PathVariable Long id) {
        try {
            deviseService.deleteDevise(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}")
    public List<Devise> getDevisesByClient(@PathVariable Long clientId) {
        return deviseService.getDevisesByClientId(clientId);
    }
}
