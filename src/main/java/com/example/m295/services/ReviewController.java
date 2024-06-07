package com.example.m295.services;

import com.example.m295.model.Review;
import com.example.m295.repository.IReviewRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import jakarta.annotation.security.*;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.*;
import com.example.m295.util.ServerErrorException;
import com.example.m295.util.NotFoundException;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Path("/api/reviews")
public class ReviewController {
    @Autowired
    private IReviewRepository iReviewRepository;

    @Autowired
    private DataSource dataSource;


    Logger logger = LoggerFactory.getLogger(ReviewController.class);


    @RolesAllowed("ADMIN")
    @DELETE
    @Path("/delete-by-date/{date}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteByDate(@PathParam("date") String dateString) {
        try {
            LocalDateTime date = LocalDateTime.parse(dateString);
            iReviewRepository.deleteAllByCreatedAt(date);
            logger.info("Deleted Reviews with date {}...", date);
            return Response.ok().entity("Successfully deleted Reviews with date: " + date).build();
        } catch (Exception e) {
            logger.error("Server could not delete Reviews with date {}...", dateString);
            throw new ServerErrorException("Couldn't delete Reviews with date: " + dateString);
        }
    }


    @RolesAllowed("USER")
    @GET
    @Path("/recommend/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> findAllByRecommend(@PathParam("status") boolean status) {
        return iReviewRepository.findAllByRecommend(status);
    }

    @PermitAll
    @GET
    @Path("/review/{review}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTripsByDestination(@PathParam("review") String review) {
        List<Review> reviews = iReviewRepository.findAllByReview(review);
        if (reviews.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok(reviews).build();
        }
    }


    @RolesAllowed("USER")
    @GET
    @Path("/exists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkExistenceById(@PathParam("id") int id) {

        if (iReviewRepository.existsById(id)) {
            logger.info("Review with id " + id + " exists");
            return Response.ok().entity("Review exists").build();
        }
        logger.warn("Review with id " + id + " doesn't exist");
        throw new NotFoundException("Review with id " + id + " does not exist");

    }

    /*
    @PermitAll
    @POST
    @Path("/sql")
    @Produces(MediaType.TEXT_PLAIN)
    public Response executeSql(){
        try(Connection connection = dataSource.getConnection()){
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("script.sql"));
            return Response.ok().build();
        }catch (Exception e){
            logger.warn("Couldn't execute SQL Script");
            throw new ServerErrorException("Server Couldn't execute SQL Script");
        }
    }
     */

    @RolesAllowed("USER")
    @POST
    @Path("/bulk-create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createMultiple(@Valid List<Review> reviews) {
        try {
            iReviewRepository.saveAll(reviews);
            logger.info("Created multiple reviews");
            return Response.ok().entity("Successfully created multiple reviews").build();
        } catch (Exception e) {
            logger.error("Server could not create multiple reviews", e);
            throw new ServerErrorException("Server couldn't create multiple reviews");
        }
    }



    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("ADMIN")
    public Response deleteAll() {
        try {
            iReviewRepository.deleteAll();
            logger.info("Deleted all players");
            return Response.ok().entity("Deleted all players").build();
        } catch (Exception e) {
            logger.error("Server could not delete players");
            throw new ServerErrorException("Server couldn't delete players");
        }
    }


    @RolesAllowed("USER")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModules() {
        try {
            var modules = iReviewRepository.findAll();

            if (modules.isEmpty()) {
                logger.warn("No Reviews found...");
                return Response.noContent().entity("No Reviews found").build();
            }
            logger.info("Found {} Reviews...", modules.size());
            return Response.ok().entity(modules).build();
        } catch (Exception e) {
            logger.error("Server could not get Reviews...");
            throw new ServerErrorException("Server couldn't get Reviews");
        }
    }





    @RolesAllowed("USER")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Optional<Review> review;
        try {
            review = iReviewRepository.findById(id);
        } catch (Exception e) {
            logger.error("Server could not get Reviews {}...", id);
            throw new ServerErrorException("Server couldn't get Reviews");
        }
        if (review.isEmpty()) {
            logger.warn("No Reviews found...");
            throw new NotFoundException("Reviews not found");
        } else {
            logger.info("Found Reviews...");
            return Response.ok().entity(review).build();
        }

    }

    @RolesAllowed("USER")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(@Valid Review review) {
        try {
            iReviewRepository.save(review);
            logger.info("Created Reviews {}...", review.getReviewId());
            return Response.ok().entity("Successfully created Reviews").build();
        } catch (Exception e) {
            logger.error("Server could not create Reviews {}...", review.getReviewId());
            throw new ServerErrorException("Server couldn't create Reviews");
        }
    }

    @RolesAllowed("ADMIN")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@Valid Review review) {
        int id = review.getReviewId();
        if (iReviewRepository.existsById(id)) {
            try {
                iReviewRepository.save(review);
                logger.info("Updated Reviews {}...", review.getReviewId());
                return Response.ok().entity("Successfully updated Reviews:\n" + review).build();
            } catch (Exception e) {
                logger.error("Server could not update Reviews {}...", review.getReviewId());
                throw new ServerErrorException("Server couldn't update Reviews");
            }
        }
        logger.warn("No Reviews with id {} found to update...", id);
        throw new NotFoundException("There is no existing Reviews with id: " + id + " to update.");
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") int id) {
        if (!iReviewRepository.existsById(id)) {
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
