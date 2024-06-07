package com.example.m295;

import com.example.m295.services.ReviewController;
import com.example.m295.services.TripController;
import com.example.m295.util.AuthenticationFilter;
import com.example.m295.util.NotFoundExceptionHandler;
import com.example.m295.util.ServerErrorExceptionHandler;
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
                        TripController.class,
                        ReviewController.class,
                        AuthenticationFilter.class,
                        ServerErrorExceptionHandler.class,
                        NotFoundExceptionHandler.class));
    }
}