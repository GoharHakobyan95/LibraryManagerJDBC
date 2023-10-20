package com.epam.library.servlets;

import com.epam.library.manager.BookManager;
import com.epam.library.manager.UserManager;
import com.epam.library.manager.impl.BookManagerImpl;
import com.epam.library.manager.impl.UserManagerImpl;
import com.epam.library.model.Book;
import com.epam.library.model.User;

import javax.servlet.http.HttpServlet;

public class GenericServlet extends HttpServlet {
    protected UserManager<Integer, User> userManager;
    protected BookManager<Integer, Book> bookManager;

    public GenericServlet() {
        userManager = new UserManagerImpl();
        bookManager = new BookManagerImpl();
    }
}
