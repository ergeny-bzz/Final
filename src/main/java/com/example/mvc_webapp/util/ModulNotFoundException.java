package com.example.mvc_webapp.util;

public class ModulNotFoundException extends RuntimeException {
    public ModulNotFoundException(String message){
        super(message);
    }
}
