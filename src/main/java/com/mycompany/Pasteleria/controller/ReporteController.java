/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Pasteleria.controller;

import com.mycompany.Pasteleria.model.Reporte;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.Pasteleria.repository.ReporteRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author gabhs
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ReporteController {
    
    @Autowired
    private ReporteRepository reporteRepository;
    
    @PostMapping("/reporte")
    public ResponseEntity<Reporte> addReporte(@RequestBody Reporte reporte) {
        try {
            Reporte report = reporteRepository.save(reporte);
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/reporte/{id}")
    public ResponseEntity<Void> deleteReporteById(@PathVariable("id") Long id) {
        try {
            reporteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/reporte")
    public ResponseEntity<Reporte> updateReporte(@RequestBody Reporte reporte) {
        Reporte report = reporteRepository.save(reporte);
        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }
    
    @GetMapping("/reporte")
    public ResponseEntity<List<Reporte>> getAllReportes() {
        try {
            List<Reporte> reportes = reporteRepository.findAll();
            if (reportes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reportes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reporte/{id}")
    public ResponseEntity<Reporte> getReporteById(@PathVariable("id") Long id) {
        try {
            Reporte reporte = reporteRepository.findById(id).get();
            return new ResponseEntity<>(reporte, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
