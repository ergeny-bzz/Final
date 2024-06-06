package com.example.m295;

import com.example.m295.services.Controller;
import com.example.m295.util.AuthenticationFilter;
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
                        Controller.class,
                        AuthenticationFilter.class));
    }
}