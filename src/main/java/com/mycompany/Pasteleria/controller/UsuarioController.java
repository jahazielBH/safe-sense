/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Pasteleria.controller;


import com.mycompany.Pasteleria.model.LoginForm;
import com.mycompany.Pasteleria.model.Usuario;
import com.mycompany.Pasteleria.repository.UsuarioRepository;
import com.mycompany.Pasteleria.response.Message;
import com.mycompany.Pasteleria.service.BcryptGenerator;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jahaziel
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    BcryptGenerator bcryptGenerator;

    @PostMapping("/usuario/register")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usr = new Usuario(usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(),
                        usuario.getDireccion(), usuario.getCorreo(), bcryptGenerator.passwordEncoder(usuario.getPassword()));
            
            usuarioRepository.save(usr);
            return ResponseEntity.ok(new Message("Éxito --> Usuario registrado", true, HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Message("Érror", true, HttpStatus.OK.value()));
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable("id") Long id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/usuario/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginRequest){
        List<Usuario> usuarios = usuarioRepository.findAll();
        for(Usuario usr : usuarios){
            if(usr.getCorreo().equals(loginRequest.getCorreo()) ){
                //usr.getPassword().equals(loginRequest.getPassword())
                return ResponseEntity.ok(new Message("Éxito --> Has iniciado sesión", true, HttpStatus.OK.value()));
            }else{
                return ResponseEntity.badRequest().body(new Message("Error --> Email no encontrado", false, HttpStatus.NOT_FOUND.value()));
            }
        }
        return ResponseEntity.badRequest().body(new Message("Érror", true, HttpStatus.OK.value()));
    }
    
}
