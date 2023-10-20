package com.epam.library.filter;

import com.epam.library.model.User;
import com.epam.library.model.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/add-book", "/assign", "/book/edit", "/user/edit", "/book/remove", "/user/remove", "/users"})
public class AdminAuthFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        User user = (User) request.getSession().getAttribute("user");

        if (user != null || user.getUserRole() == UserRole.ADMIN) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/error_403");
        }
    }
}