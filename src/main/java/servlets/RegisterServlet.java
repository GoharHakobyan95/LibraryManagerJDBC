package com.epam.library.servlets;

import com.epam.library.manager.UserManager;
import com.epam.library.manager.impl.UserManagerImpl;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends GenericServlet {

    private UserManager<Integer, User> userManager;

    public RegisterServlet() {
        userManager = new UserManagerImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            req.setAttribute("passwordMatchError", "Password did not match with confirm password");
            req.getRequestDispatcher("/registerPage").forward(req, resp);
            return;
        }

        User user = User.builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .password(password)
                .confirmPassword(confirmPassword)
                .userRole(UserRole.USER)
                .build();
        userManager.save(user);

        req.getRequestDispatcher("/").forward(req, resp);

    }
}
