package servlets;

import com.google.gson.Gson;
import service.LabWorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/by_author/*")
public class ByAuthorServlet extends HttpServlet {
    private Gson gson;
    private LabWorkService service;

    @Override
    public void init() {
        gson = new Gson();
        service = LabWorkService.getInstance();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] path = req.getPathInfo().split("/");
        String author = path[1];
        resp.getWriter().write(gson.toJson(service.deleteSingleByAuthor(author)));
    }
}
