package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.LabWorkDTO;
import service.LabWorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet(value = "/calc_by_minimal_point/*")
public class MinimalPointServlet extends HttpServlet {
    private Gson gson;
    private LabWorkService service;

    @Override
    public void init() {
        gson = new Gson();
        service = LabWorkService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] path = req.getPathInfo().split("/");
        Double minimalPoint = Double.parseDouble(path[1]);
        resp.getWriter().write(service.countMinimalPointGreaterThan(minimalPoint).toString());
    }
}
