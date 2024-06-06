package com.example.m295.services;


import com.example.m295.model.Trip;
import com.example.m295.repository.ITripRepository;
import jakarta.annotation.security.*;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.example.m295.util.ServerErrorException;
import com.example.m295.util.NotFoundException;


import java.util.Optional;

@Component
@Path("/api/rooms")
public class TripController {

    @Autowired
    private ITripRepository iTripRepository;

    Logger logger = LoggerFactory.getLogger(TripController.class);

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRooms() {
        try {
            var rooms = iTripRepository.findAll();

            if (rooms.isEmpty()) {
                logger.warn("No rooms found...");
                return Response.noContent().entity("No rooms found").build();
            }
            logger.info("Found {} rooms...", rooms.size());
            return Response.ok().entity(rooms).build();
        } catch (Exception e) {
            logger.error("Server could not get rooms...");
            throw new ServerErrorException("Server couldn't get rooms");
        }
    }

    @PermitAll
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Optional<Trip> trip;
        try {
            trip = iTripRepository.findById(id);
        } catch (Exception e) {
            logger.error("Server could not get room {}...", id);
            throw new ServerErrorException("Server couldn't get room");
        }
        if (trip.isEmpty()) {
            logger.warn("No room found...");
            throw new NotFoundException("room not found");
        } else {
            logger.info("Found room...");
            return Response.ok().entity(trip).build();
        }

    }

    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(@Valid Trip trip) {
        try {
            iTripRepository.save(trip);
            logger.info("Created room {}...", trip.getTripId());
            return Response.ok().entity("Successfully created room").build();
        } catch (Exception e) {
            logger.error("Server could not create room {}...", trip.getDestination());
            throw new ServerErrorException("Server couldn't create room");
        }
    }

    @PermitAll
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@Valid Trip trip) {
        int id = trip.getTripId();
        if (iTripRepository.existsById(id)) {
            try {
                iTripRepository.save(trip);
                logger.info("Updated room {}...", trip.getTripId());
                return Response.ok().entity("Successfully updated room:\n" + trip).build();
            } catch (Exception e) {
                logger.error("Server could not update room {}...", trip.getTripId());
                throw new ServerErrorException("Server couldn't update room");
            }
        }
        logger.warn("No room with id {} found to update...", id);
        throw new NotFoundException("There is no existing room with id: " + id + " to update.");
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") int id) {
        if (!iTripRepository.existsById(id)) {
            logger.warn("No room with id {} found to delete", id);
            throw new NotFoundException("There is no existing room with id: " + id + " to delete");
        }
        try {
            iTripRepository.deleteById(id);
            logger.info("Deleted room {}...", id);
            return Response.ok().entity("Successfully deleted room " + id).build();
        } catch (Exception e) {
            logger.error("Server could not delete room {}...", id);
            throw new ServerErrorException("Couldn't delete room " + id);
        }
    }

}