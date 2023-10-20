package com.epam.library.filter;

import com.epam.library.model.User;
import com.epam.library.model.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/my-account/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("user");

        if (user.getUserRole().equals(UserRole.USER)) {
            req.getRequestDispatcher("/WEB-INF/userDashboard.jsp").forward(req, resp);
        } else if (user.getUserRole().equals(UserRole.ADMIN)) {
            req.getRequestDispatcher("/WEB-INF/adminDashboard.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}


