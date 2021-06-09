/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.safe.response;

import com.mycompany.safe.model.Usuario;

/**
 *
 * @author jahaziel
 */
public class Message {

    private String message;
    private Boolean isSuccess;
    private Integer code;
    private Usuario usuario;

    public Message(String message, Boolean isSuccess, Integer code, Usuario usuario) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.code = code;
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }
    
    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
