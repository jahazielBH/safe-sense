/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.safe.controller;

import com.mycompany.safe.model.LoginForm;
import com.mycompany.safe.model.Usuario;
import com.mycompany.safe.repository.UsuarioRepository;
import com.mycompany.safe.response.Message;
import com.mycompany.safe.service.BcryptGenerator;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
    AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    BcryptGenerator bcryptGenerator;

    @PostMapping("/usuario/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            if (usuario.getNombre().equals("") || usuario.getApellido().equals("") || usuario.getTelefono().equals("") || usuario.getDireccion().equals("") || usuario.getCorreo().equals("") || usuario.getPassword().equals("")) {
                return ResponseEntity.badRequest().body(new Message("Error --> Campos requeridos", false, HttpStatus.BAD_REQUEST.value(), null));
            } else if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
                return ResponseEntity.badRequest().body(new Message("Error --> Correo electrónico registrado", false, HttpStatus.BAD_REQUEST.value(), null));
            } else {
                Usuario usr = new Usuario(usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(),
                        usuario.getDireccion(), usuario.getCorreo(), bcryptGenerator.passwordEncoder(usuario.getPassword()));
                usuarioRepository.save(usr);
                return ResponseEntity.ok(new Message("Éxito --> Usuario registrado", true, HttpStatus.OK.value(), usr));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Message("Érror", true, HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
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
    
    @PutMapping("/usuario")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Usuario usr = usuarioRepository.save(usuario);
        return new ResponseEntity<>(usr, HttpStatus.CREATED);
    }

    @PostMapping("/usuario/login")
    public ResponseEntity<?> authentication(@Valid @RequestBody LoginForm loginRequest) {
        try {
            String correo = loginRequest.getCorreo();
            String password = loginRequest.getPassword();
            if (loginRequest.getCorreo().equals("") || loginRequest.getPassword().equals("")) {
                return ResponseEntity.badRequest().body(new Message("Error --> Credenciales requeridas", false, HttpStatus.BAD_REQUEST.value(), null));
            } else if (usuarioRepository.existsByCorreo(correo)) {
                Usuario usr = usuarioRepository.findByCorreo(correo);
                if (bcryptGenerator.passwordDecoder(password, usr.getPassword())) {
                    return ResponseEntity.ok(new Message("Éxito --> Has iniciado sesión", true, HttpStatus.OK.value(), usr));
                } else {
                    return ResponseEntity.badRequest().body(new Message("Error --> Contraseña incorrecta", false, HttpStatus.UNAUTHORIZED.value(), null));
                }

            } else {
                return ResponseEntity.badRequest().body(new Message("Error --> Correo electrónico no encontrado", false, HttpStatus.NOT_FOUND.value(), null));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
