package Controllers;

import org.apache.logging.log4j.LogManager;
import Entities.*;
import DBMethods.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        logger.info("authMethod");
        PrintWriter writer = response.getWriter();
        if(!loginAuth.dbMethod()){
            writer.println("error");
        }else {
            writer.println("fine");
            session.setAttribute("userLogin", loginAuth.getLoginAuth());
            Cookie cookie = new Cookie("userLogin", loginAuth.getLoginAuth());
            response.addCookie(cookie);
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
        Cookie cookie = new Cookie("userLogin", loginReg.getLoginReg());
        response.addCookie(cookie);
    }

    @POST
    @Path("/check")
    public void checkMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        logger.warn(cookies.length);
        String stringUser = "userLogin";
        PrintWriter writer = response.getWriter();
        boolean trigger = false;
        for(Cookie c: cookies) {
            logger.warn(c.getName() + " cook");
            if(stringUser.equals(c.getName())) {
                CheckUser checkUser = new CheckUser(c.getValue());
                if (checkUser.checkUserMethod()){
                    logger.warn("fine " + c.getValue());
                    writer.println(c.getValue());
                    session.setAttribute("userLogin", c.getValue());
                }else {
                    logger.warn("wrong");
                    writer.println("wrong");
                }
                trigger = true;
                break;
            }
        }
        if (!trigger){
            logger.warn("wrong trigger");
            writer.println("wrong");
        }
        writer.flush();
        writer.close();
    }

    @POST
    @Path("/logout")
    public void logoutMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String stringUser = "userLogin";
        for (Cookie cookie : cookies) {
                logger.warn("Is " + cookie.getName() + " and " + cookie.getValue());
                cookie.setValue("");
                cookie.setMaxAge(0);
                logger.warn("Is " + cookie.getName() + " and " + cookie.getValue());
                response.addCookie(cookie);
                logger.warn("Ok");
        }
        session.removeAttribute("userLogin");
    }
}
