package controllers;

import com.google.gson.Gson;
import service.LabWorkService;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("calc_by_minimal_point")
public class MinimalPointController extends Application {
    private Gson gson;
    private LabWorkService service;

    @PostConstruct
    public void init() {
        gson = new Gson();
        service = LabWorkService.getInstance();
    }

    @GET
    @Path("{minimal-point}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countMinimalPoint(@PathParam("minimal-point") Double minimalPoint) {
        return Response.status(200).entity(service.countMinimalPointGreaterThan(minimalPoint).toString()).build();
    }
}
