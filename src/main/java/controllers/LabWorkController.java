package controllers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import dto.FilterValuesDTO;
import dto.LabWorkDTO;
import dto.ReceivedLabWorkDTO;
import service.LabWorkService;
import utils.ZDTAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("lab-works")
public class LabWorkController extends Application {

    private Gson gson;
    private LabWorkService service;

    @PostConstruct
    public void init() {
        gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(ZonedDateTime.class, new ZDTAdapter()).create();
        service = LabWorkService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLabWorks(@Context UriInfo uriInfo) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        FilterValuesDTO filterValuesDTO = new FilterValuesDTO();
        String fieldValue = queryParams.getFirst("id");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setId(fieldValue);
        }
        fieldValue = queryParams.getFirst("name");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setName(fieldValue);
        }
        fieldValue = queryParams.getFirst("coordinate_id");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setCoordinate_id(fieldValue);
        }
        fieldValue = queryParams.getFirst("minimalPoint");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setMinimalPoint(fieldValue);
        }
        fieldValue = queryParams.getFirst("difficulty");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setDifficulty(fieldValue);
        }
        fieldValue = queryParams.getFirst("person_id");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setPerson_id(fieldValue);
        }

        List<LabWorkDTO> labWorkList = service.getAll(filterValuesDTO);
        Type listType = new TypeToken<List<LabWorkDTO>>() {
        }.getType();
        return Response.status(200).entity(gson.toJson(labWorkList, listType)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getLabWorkById(@PathParam("id") Integer id) {
        LabWorkDTO labWorkDTO= service.getById(id);
        return Response.status(200).entity(gson.toJson(labWorkDTO)).build();
    }



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLabWork(ReceivedLabWorkDTO labWork) {
        //validate
//        ReceivedLabWorkDTO labWork = gson.fromJson(body, ReceivedLabWorkDTO.class);
        return Response.status(200).entity(gson.toJson(service.create(labWork))).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLabWork(ReceivedLabWorkDTO labWork) {
        return Response.status(200).entity(gson.toJson(service.update(labWork))).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLabWork(@PathParam("id") Integer id) {
        service.delete(id);
        return Response.status(200).build();
    }
}
