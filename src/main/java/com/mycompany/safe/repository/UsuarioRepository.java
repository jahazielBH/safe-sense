/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.safe.repository;




import com.mycompany.safe.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jahaziel
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByCorreo(String correo);
    public boolean existsByCorreo(String correo);
}
