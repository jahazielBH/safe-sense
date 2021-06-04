/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Pasteleria.response;

/**
 *
 * @author jahaziel
 */
public class Message {

    private String message;
    private Boolean isSuccess;
    private Integer code;

    public Message(String message, Boolean isSuccess, Integer code) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.code = code;
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

}
