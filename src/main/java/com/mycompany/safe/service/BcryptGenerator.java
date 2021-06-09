/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.safe.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author jahaziel
 */
@Service
public class BcryptGenerator {
    
    public String passwordEncoder(String password) {
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String hashedPassword = passwordEncoder.encode(password);
         return  hashedPassword;
    }
    
    public Boolean passwordDecoder(String currentPassword, String ExistingPassword) {
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         if (passwordEncoder.matches(currentPassword, ExistingPassword)) {
                    return  true;
         } else {
             return false;
         }
     }
}
