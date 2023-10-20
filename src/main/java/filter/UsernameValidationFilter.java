package com.epam.library.filter;


import com.epam.library.manager.UserManager;
import com.epam.library.manager.impl.UserManagerImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebFilter(value = "/register")
public class UsernameValidationFilter implements Filter {
    private UserManager userManager = new UserManagerImpl();
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF8");
        String email = req.getParameter("email");
        Matcher matcher = pattern.matcher(email);
        if (userManager.getByEmail(email) != null) {
            req.setAttribute("usernameExistsErr", "Oops !!! Username already exists . ");
            req.getRequestDispatcher("/registerPage").forward(req, resp);
        } else if (matcher.matches()) {
            chain.doFilter(req, resp);
        } else {
            req.setAttribute("emailFormatErr", "Invalid email format . ");
            req.getRequestDispatcher("/registerPage").forward(req, resp);
        }
    }

}
