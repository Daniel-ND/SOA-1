package servlets;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import dto.FilterValuesDTO;
import dto.LabWorkDTO;
import dto.ReceivedLabWorkDTO;
import service.LabWorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(value = "/lab-works/*")
public class LabWorkServlet extends HttpServlet {

    private class ZDTAdapter implements JsonSerializer<ZonedDateTime> {

        @Override
        public JsonElement serialize(ZonedDateTime zonedDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(zonedDateTime.toLocalDateTime().format(DateTimeFormatter.ISO_DATE));
        }
    }

    private Gson gson;
    private LabWorkService service;

    @Override
    public void init() {
        gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(ZonedDateTime.class, new ZDTAdapter()).create();
        service = LabWorkService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilterValuesDTO filterValuesDTO = new FilterValuesDTO();
        String fieldValue = req.getParameter("id");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setId(fieldValue);
        }
        fieldValue = req.getParameter("name");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setName(fieldValue);
        }
        fieldValue = req.getParameter("coordinate_id");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setCoordinate_id(fieldValue);
        }
        fieldValue = req.getParameter("minimalPoint");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setMinimalPoint(fieldValue);
        }
        fieldValue = req.getParameter("difficulty");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setDifficulty(fieldValue);
        }
        fieldValue = req.getParameter("person_id");
        if (!(fieldValue == null || fieldValue.trim().isEmpty())) {
            filterValuesDTO.setPerson_id(fieldValue);
        }

        List<LabWorkDTO> labWorkList = service.getAll(filterValuesDTO);
        resp.setContentType("application/json");
        Type listType = new TypeToken<List<LabWorkDTO>>() {}.getType();
        resp.getWriter().write(gson.toJson(labWorkList, listType));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //validate
        ReceivedLabWorkDTO labWork = gson.fromJson(getBody(req), ReceivedLabWorkDTO.class);
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(service.create(labWork)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //validate
        //System.out.println(getBody(req));
        ReceivedLabWorkDTO labWork = gson.fromJson(getBody(req), ReceivedLabWorkDTO.class);
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(service.update(labWork)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] path = req.getPathInfo().split("/");
        Integer id = Integer.parseInt(path[1]);
        service.delete(id);
    }

    private static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
