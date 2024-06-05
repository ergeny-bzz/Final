package com.example.mvc_webapp.util;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.http.HttpStatus;

@Provider
public class ModulNotFoundExceptionHandler implements ExceptionMapper<ModulNotFoundException> {
    @Override
    public Response toResponse(ModulNotFoundException ex) {
        return Response.status(HttpStatus.SC_NOT_FOUND).entity(ex.getMessage()).build();
    }
}