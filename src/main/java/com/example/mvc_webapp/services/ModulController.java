package com.example.mvc_webapp.services;

import com.example.mvc_webapp.model.Modul;
import com.example.mvc_webapp.repository.IModuleRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Path("/api/modules")
public class ModulController {

    Logger logger = LoggerFactory.getLogger(ModulController.class);


    @Autowired
    private IModuleRepository moduleRepository;

    @RolesAllowed("ADMIN")
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

    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        Optional<Modul> module;
        try {
            module = moduleRepository.findById(id);
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        if (module.isEmpty()) {
            logger.warn("No Module Found");
        }
        logger.info("Found Module!");
        return Response.ok().entity(module).build();

    }

    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Modul module) {
        try {
            moduleRepository.save(module);
            logger.info("Created module {}...", module.getBezeichnung());
            return Response.ok().entity("Successfully created module").build();
        } catch (Exception e) {
            logger.error("Server could not create module {}...", module.getBezeichnung());
            return Response.serverError().entity("Server Error").build();
        }
    }

    @PermitAll
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(Modul module) {
        if (module.getModulNr() == null || module.getBezeichnung() == null || module.getStart() == null || module.getEnde() == null || module.getKosten() == null || module.getAktiv() == null) {
            logger.warn("Missing required Fields");
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing required fields").build();
        }

        if (moduleRepository.existsById(module.getModulNr())) {
            try {
                moduleRepository.save(module);
                return Response.ok().entity("Successfully updated module:\n" + module).build();
            } catch (Exception e) {
                return Response.serverError().entity("Failed to update module:\n" + module).build();
            }
        }
        return Response.status(404).entity("There is no existing module with id: " + module.getModulNr() + " to update.").build();
    }

    @PermitAll
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") int id) {
        if (moduleRepository.existsById(id)) {
            try {
                moduleRepository.deleteById(id);
                return Response.ok().entity("Successfully deleted module").build();
            } catch (Exception e) {
                return Response.serverError().entity(e.getMessage()).build();
            }
        }
        return Response.status(404).entity("There is no existing module with id: " + id + " to delete.").build();
    }
}
