package com.epam.library.servlets;


import com.epam.library.model.Book;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-account/user")
public class UserServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        List<Book> userAllBooks = bookManager.getUserAllBooks(user.getId());
        req.getSession().setAttribute("userAllBooks", userAllBooks);
        req.getRequestDispatcher("/WEB-INF/userDashboard.jsp").forward(req, resp);
    }
}


