package com.epam.library.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter("/register")
public class PasswordValidationFilter implements Filter {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("UTF8");
        String password = req.getParameter("password");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("passwordErrMsg", "Invalid password format. Please follow the password requirements.");
            HttpServletResponse resp = (HttpServletResponse) response;
            req.getRequestDispatcher("/registerPage").forward(req, resp);
        }
    }

}
