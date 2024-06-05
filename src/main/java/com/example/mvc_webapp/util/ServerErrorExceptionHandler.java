package com.example.mvc_webapp.util;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServerErrorExceptionHandler implements ExceptionMapper<ServerErrorException> {
    @Override
    public Response toResponse(ServerErrorException e) {
        return Response.serverError().entity(e.getMessage()).build();
    }
}
