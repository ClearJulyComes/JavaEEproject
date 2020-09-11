package Controllers;


import DBMethods.AddFriend;
import DBMethods.LoginAuth;
import Entities.UserInfo;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AuthFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        logger.warn(sessionLogin + " Login in SESSION");
        if(("/auth".equals( req.getRequestURI().substring(req.getContextPath().length()) ) )||
                ("/".equals( req.getRequestURI().substring(req.getContextPath().length()) ))){
            logger.warn(sessionLogin + " Login in SESSION 1");
            filterChain.doFilter(req, resp);
            return;
        }
        if((sessionLogin==null)||(sessionLogin.equals(""))){
            resp.sendRedirect(req.getContextPath());
        }else {
            logger.warn(sessionLogin + " Login in SESSION 2");
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
