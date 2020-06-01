import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.PrintWriter;

@Path("/auth")
public class MainController {

    @POST
    public void authMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h2>Hello from HelloServlet</h2>");
        } */
        response.sendRedirect("https://google.com/");
    }

    @GET
    public String tat(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h2>Hello from HelloServlet</h2>");
        } */
        response.sendRedirect("https://google.com/");
        return null; //TODO replace this stub to something useful
    }
}
