package Controllers;

import org.apache.logging.log4j.LogManager;
import Entities.*;
import DBMethods.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.PrintWriter;

@Path("/log")
public class LoginController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoginController.class);

    @POST
    @Path("/auth")
    public void authMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text;charset=UTF-8");
        HttpSession session = request.getSession();
        LoginAuth loginAuth;
        loginAuth = new LoginAuth(request.getParameter("login"), request.getParameter("password"));
        loginAuth.dbMethod();
        logger.info("authMethod");
        PrintWriter writer = response.getWriter();
        if(loginAuth.getLoginAuth() == null){
            logger.info("isEmpty");
            writer.println("shiiit");
        }else {
            logger.info("return true");
            writer.println("fine");
            session.setAttribute("userLogin", loginAuth.getLoginAuth());
        }
        writer.flush();
        writer.close();
    }

    @POST
    @Path("/reg")
    public void regMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        LoginReg loginReg = new LoginReg(request.getParameter("login"), request.getParameter("password"));
        loginReg.dbMethod();
        session.setAttribute("userLogin", loginReg.getLoginReg());
    }
}
