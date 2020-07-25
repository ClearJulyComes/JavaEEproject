import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@Path("/log")
public class MainController {
    private static Logger log = Logger.getLogger(MainController.class.getName());

    @POST
    @Path("/auth")
    public void authMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        log.info("Auth");
        Login login = new Login(request.getParameter("login"), request.getParameter("password"));
        final PrintWriter writer = response.getWriter();
        login.dbMethod();
        writer.println(request.getParameter("login"));
        log.info("oki");
    }

    @POST
    @Path("/reg")
    public void regMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //try (PrintWriter writer = response.getWriter()) {
        //    writer.println("<h2>Hello from HelloServlet</h2>");
        //}
        log.info("oki");
    }

    @GET
    public String tat(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h2>Hello from HelloServlet</h2>");
        }
        //response.sendRedirect("https://google.com/");
        return "ok"; //TODO replace this stub to something useful
    }
}
