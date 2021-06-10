/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.safe.repository;

import com.mycompany.safe.model.Reporte;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gabhs
 */
public interface ReporteRepository extends JpaRepository<Reporte, Long>{
    public Reporte findTopByOrderByIdDesc();
    public List<Reporte> findByNombreSensor(String nombre_sensor);
    public List<Reporte> findByFecha(String fecha);
}
