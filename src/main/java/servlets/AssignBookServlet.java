package com.epam.library.servlets;

import com.epam.library.model.Book;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/assign")
public class AssignBookServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userManager.getAll();
        req.getSession().setAttribute("users", users);

        List<Book> unassignedBooks = bookManager.getAllUnassignedBooks();
        req.getSession().setAttribute("unassignedBooks", unassignedBooks);
        req.getRequestDispatcher("/WEB-INF/assign.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer selectedUser = Integer.valueOf(req.getParameter("selectedUser"));
            Integer selectedBook = Integer.valueOf(req.getParameter("selectedBook"));

            User user = userManager.getById(selectedUser);
            if (user != null) {
                Book book = bookManager.getById(selectedBook);
                if (book != null && book.getUserId() == 0) {
                    book.setUserId(selectedUser);
                    bookManager.update(book);
                    List<Book> unassignedBooks = bookManager.getAllUnassignedBooks();
                    req.getSession().setAttribute("unassignedBooks", unassignedBooks);
                    req.setAttribute("successAssign","Successfully assigned!");
                    req.getRequestDispatcher("/WEB-INF/assign.jsp").forward(req, resp);
                }
            }

    }
}
