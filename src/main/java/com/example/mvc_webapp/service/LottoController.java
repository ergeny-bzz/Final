package com.example.mvc_webapp.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/api")
public class LottoController {
    private List<Integer> tips = new ArrayList<>();
    private final List<Integer> solutions = new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
    }};

    @GET
    @Path("/getNumbers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumbers() {
        try {
            return Response.ok().entity(solutions).build();
        } catch (Exception e) {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/getHits")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHits() {
        if (tips.isEmpty()) {
            return Response.status(400).entity("No tips").build();
        }
        try {
            List<Integer> hits = new ArrayList<>(tips);
            hits.retainAll(solutions);
            return Response.ok().entity(hits).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/addTips")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addTips(Integer[] array) {
        if (array.length != 6) return Response.status(400).entity("Need more tips").build();
        try {
            tips = Arrays.asList(array);
            return Response.ok().entity("Successfully added tips").build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}