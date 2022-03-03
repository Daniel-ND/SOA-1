package controllers;

import com.google.gson.Gson;
import service.LabWorkService;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("by_author")
public class ByAuthorController extends Application {
    private Gson gson;
    private LabWorkService service;

    @PostConstruct
    public void init() {
        gson = new Gson();
        service = LabWorkService.getInstance();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello BY AUTHOR HTML" + "</h1></body>" + "</html> ";
    }

    @DELETE
    @Path("{author}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByAuthor(@PathParam("author") String author)  {
        return Response.status(200).entity(gson.toJson(service.deleteSingleByAuthor(author))).build();
    }
}
