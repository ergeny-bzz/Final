package com.example.m295.util;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.http.HttpStatus;

@Provider
public class
NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException ex) {
        return Response.status(HttpStatus.SC_NOT_FOUND).entity(ex.getMessage()).build();
    }
}