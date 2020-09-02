package Controllers;


import DBMethods.LoginAuth;
import Entities.UserInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();
        if(("/log/auth".equals( req.getRequestURI().substring(req.getContextPath().length()) ) )||
                ("/log/reg".equals( req.getRequestURI().substring(req.getContextPath().length()) ))){
            filterChain.doFilter(req, resp);
            return;
        }
        String sessionLogin = (String) session.getAttribute("userLogin");
        if(sessionLogin==null){
            resp.sendRedirect(req.getContextPath());
        }else {
            filterChain.doFilter(req, resp);
        }
        /*
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserInfo userInfo = entityManager.find(UserInfo.class, sessionLogin);
        if(userInfo.getUserPassword()!=null){
            filterChain.doFilter(req, resp);
            return;
        } else {

        } */
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
