package com.epam.library.servlets;

import com.epam.library.model.Book;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-book")
public class AddBookServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/adminDashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String bookName = req.getParameter("bookName");
        String authorName = req.getParameter("authorName");

        Book book = Book.builder()
                .bookName(bookName)
                .authorName(authorName)
                .build();

        bookManager.save(book);

        req.setAttribute("bookAddingMsg", " Book has been added to the library.");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/adminDashboard.jsp").forward(req, resp);

    }
}
