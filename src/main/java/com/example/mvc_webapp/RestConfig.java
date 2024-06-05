package com.example.mvc_webapp;

import com.example.mvc_webapp.service.LottoController;
import com.example.mvc_webapp.service.ModulController;
import com.example.mvc_webapp.util.AuthenticationFilter;
import com.example.mvc_webapp.util.ModulNotFoundExceptionHandler;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resources")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(
                Arrays.asList(
                        ModulController.class,
                        LottoController.class,
                        ModulNotFoundExceptionHandler.class,
                        AuthenticationFilter.class));
    }
}