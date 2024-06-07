package com.example.m295.services;


import com.example.m295.model.Trip;
import com.example.m295.repository.IReviewRepository;
import com.example.m295.repository.ITripRepository;
import jakarta.annotation.security.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.example.m295.util.ServerErrorException;
import com.example.m295.util.NotFoundException;


import java.util.List;
import java.util.Optional;

@Component
@Path("/api/trips")
public class TripController{

    @Autowired
    private ITripRepository iTripRepository;
    @Autowired
    private IReviewRepository iReviewRepository;

    Logger logger = LoggerFactory.getLogger(TripController.class);

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrips() {
        try {
            var rooms = iTripRepository.findAll();

            if (rooms.isEmpty()) {
                logger.warn("No Trips found...");
                return Response.noContent().entity("No Trips found").build();
            }
            logger.info("Found {} Trips...", rooms.size());
            return Response.ok().entity(rooms).build();
        } catch (Exception e) {
            logger.error("Server could not get Trips...");
            throw new ServerErrorException("Server couldn't get Trips");
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
            logger.error("Server could not get Trip {}...", id);
            throw new ServerErrorException("Server couldn't get Trip");
        }
        if (trip.isEmpty()) {
            logger.warn("No Trip found...");
            throw new NotFoundException("Trip not found");
        } else {
            logger.info("Found Trip...");
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
            logger.info("Created Trip {}...", trip.getTripId());
            return Response.ok().entity("Successfully created Trip").build();
        } catch (Exception e) {
            logger.error("Server could not create Trip {}...", trip.getDestination());
            throw new ServerErrorException("Server couldn't create Trip");
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
                logger.info("Updated Trip {}...", trip.getTripId());
                return Response.ok().entity("Successfully updated Trip:\n" + trip).build();
            } catch (Exception e) {
                logger.error("Server could not update Trip {}...", trip.getTripId());
                throw new ServerErrorException("Server couldn't update Trip");
            }
        }
        logger.warn("No Trip with id {} found to update...", id);
        throw new NotFoundException("There is no existing Trip with id: " + id + " to update.");
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") int id) {
        if (!iTripRepository.existsById(id)) {
            logger.warn("No Reviews with id {} found to delete", id);
            throw new NotFoundException("There is no existing Reviews with id: " + id + " to delete");
        }
        try {
            iReviewRepository.deleteById(id);
            logger.info("Deleted Reviews {}...", id);
            return Response.ok().entity("Successfully deleted Reviews " + id).build();
        } catch (Exception e) {
            logger.error("Server could not delete module {}...", id);
            throw new ServerErrorException("Couldn't delete Reviews " + id);
        }
    }

    }



