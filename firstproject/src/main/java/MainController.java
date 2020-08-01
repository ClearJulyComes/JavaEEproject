import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@Path("/log")
public class MainController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MainController.class);

    @POST
    @Path("/auth")
    public void authMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        LoginAuth loginAuth = new LoginAuth(request.getParameter("login"), request.getParameter("password"));
        logger.info("authMethod");
        loginAuth.dbMethod();
        if(loginAuth.getLoginAuth() == null){
            logger.info("isEmpty");
            writer.println("shiiit");
        }else {
            logger.info("return true");
            writer.println("fine");
        }
        writer.flush();
        writer.close();
    }

    @POST
    @Path("/reg")
    public void regMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        LoginReg loginReg = new LoginReg(request.getParameter("login"), request.getParameter("password"));
        loginReg.dbMethod();
    }
}
