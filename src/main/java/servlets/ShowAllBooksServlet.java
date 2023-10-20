package com.epam.library.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books")
public class ShowAllBooksServlet extends GenericServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userManager.getAll());
        req.setAttribute("books", bookManager.getAll());

        req.getRequestDispatcher("/WEB-INF/showAllBooks.jsp").forward(req, resp);
    }
}
