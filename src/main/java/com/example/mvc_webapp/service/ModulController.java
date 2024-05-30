package com.example.mvc_webapp.service;

import com.example.mvc_webapp.model.Modul;
import com.example.mvc_webapp.repository.IModuleRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/api/modules")
public class ModulController {
    @Autowired
    private IModuleRepository moduleRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModules() {
        try {
            var modules = moduleRepository.findAll();
            return Response.ok().entity(modules).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        try {
            var module = moduleRepository.findById(1);
            return Response.ok().entity(module).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Modul module) {
        try {
            moduleRepository.save(module);
            return Response.ok().entity("Successfully created module").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}