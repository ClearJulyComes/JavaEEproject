import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@Path("/log")
public class MainController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MainController.class);

    @POST
    @Path("/auth")
    public void authMethod(@CookieParam("session") Cookie cookie, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text;charset=UTF-8");
        String sessionCookie = cookie.getValue();
        LoginAuth loginAuth;
        if(!cookie.getValue().isEmpty()) {
            loginAuth = new LoginAuth(request.getParameter("login"), null);
            loginAuth.dbMethodCookie();
        }else{
            loginAuth = new LoginAuth(request.getParameter("login"), request.getParameter("password"));
            loginAuth.dbMethod();
        }
        logger.info("authMethod");
        PrintWriter writer = response.getWriter();
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
