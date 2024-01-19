package tn.atch.acrh.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.atch.acrh.models.Reglement;
@Controller
public class ReglementController {
//
//    @Autowired
//    private ReglementService reglementService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Reglement> getById(@PathVariable Long id) {
//        try {
//            Reglement reglement = reglementService.getById(id);
//            return new ResponseEntity<>(reglement, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Reglement> createReglement(@RequestBody Reglement reglement) {
//        Reglement createdReglement = reglementService.create(reglement);
//        return new ResponseEntity<>(createdReglement, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Reglement> updateReglement(@PathVariable Long id, @RequestBody Reglement reglement) {
//        try {
//            Reglement updatedReglement = reglementService.update(id, reglement);
//            return new ResponseEntity<>(updatedReglement, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteReglement(@PathVariable Long id) {
//        try {
//            reglementService.delete(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
