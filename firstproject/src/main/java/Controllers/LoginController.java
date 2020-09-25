package Controllers;

import org.apache.logging.log4j.LogManager;
import DBMethods.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Контроллер для обработки HTTP запросов по регистрации и авторизации.
 */
@Path("/log")
public class LoginController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoginController.class);

    /**
     * POST запрос для прохождения авторизации. В случае успеха логин сохраняется в cookie.
     * @param request входящий параметр
     * @param response ответ сервера
     * @throws IOException исключение
     */
    @POST
    @Path("/auth")
    public void authMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
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

    /**
     * POST запрос для регистрации. В случае успешного запроса в БД, добавляем логин в cookie и session.
     * @param request входящий параметр
     * @param response ответ сервера
     * @throws ServletException исключение
     * @throws IOException исключение
     */
    @POST
    @Path("/reg")
    public void regMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        LoginReg loginReg = new LoginReg(request.getParameter("login"), request.getParameter("password"));
        loginReg.dbMethod();
        session.setAttribute("userLogin", loginReg.getLoginReg());
        Cookie cookie = new Cookie("userLogin", loginReg.getLoginReg());
        response.addCookie(cookie);
    }

    /**
     * POST запрос для проверки cookie пользователя, на случай того, что он уже зарегистрирован.
     * @param request входящий параметр
     * @param response ответ сервера
     * @throws IOException исключение
     */
    @POST
    @Path("/check")
    public void checkMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String stringUser = "userLogin";
        PrintWriter writer = response.getWriter();
        boolean trigger = false;
        for(Cookie c: cookies) {
            if(stringUser.equals(c.getName())) {
                CheckUser checkUser = new CheckUser(c.getValue());
                if (checkUser.checkUserMethod()){
                    writer.println(c.getValue());
                    session.setAttribute("userLogin", c.getValue());
                }else {
                    writer.println("wrong");
                }
                trigger = true;
                break;
            }
        }
        if (!trigger){
            writer.println("wrong");
        }
        writer.flush();
        writer.close();
    }

    /**
     * POST запрос для логаута. Очищаются cookie и session.
     * @param request входящий параметр
     * @param response ответ сервера
     */
    @POST
    @Path("/logout")
    public void logoutMethod(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
        }
        session.removeAttribute("userLogin");
    }
}
