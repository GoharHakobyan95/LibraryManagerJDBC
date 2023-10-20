package com.epam.library.manager.impl;

import com.epam.library.db.DBConnectionProvider;
import com.epam.library.manager.BookManager;
import com.epam.library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookManagerImpl implements BookManager<Integer, Book> {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    @Override
    public Book getById(Integer id) {
        String sql = "SELECT * FROM book  WHERE id = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getBooksFromResulSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Book> getAll() {
        String sql = "SELECT * FROM book ";
        List<Book> books = new LinkedList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                books.add(getBooksFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return books;
    }

    @Override
    public void save(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book(book_name, author_name) VALUES(?,?)");
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            int execute = preparedStatement.executeUpdate();
            System.out.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        if (book != null) {
            connection = DBConnectionProvider.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE book SET book_name=?, author_name=?, user_id=? WHERE id=?"
                );
                preparedStatement.setString(1, book.getBookName());
                preparedStatement.setString(2, book.getAuthorName());
                preparedStatement.setInt(3, book.getUserId());
                preparedStatement.setInt(4, book.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer bookId) {
        String sql = "Delete from book where id = " + bookId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Book> getAllUnassignedBooks() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book where user_id is null");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setBookName(resultSet.getString("book_name"));
                book.setAuthorName(resultSet.getString("author_name"));
                book.setUserId(resultSet.getInt("user_id"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getAllAssignedBooks() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book where user_id is not null");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                books.add(getBooksFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void delete(Integer bookId, Integer userId) {
        String sql = "delete from book where id = ? AND user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unAssign(Book book) {
        String sql = "UPDATE book SET user_id = NULL WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getByUserId(Integer userId) {
        String sql = "SELECT * FROM book  WHERE user_id = " + userId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getBooksFromResulSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Book> getUserAllBooks(Integer userId) {
        String sql = "SELECT book_name, author_name FROM book WHERE user_id = ?";
        List<Book> books = new LinkedList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                books.add(getBooksFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return books;
    }

    private Book getBooksFromResulSet(ResultSet resultSet) throws SQLException {
        return Book.builder()
                .id(resultSet.getInt("id"))
                .bookName(resultSet.getString("book_name"))
                .authorName(resultSet.getString("author_name"))
                .userId(resultSet.getInt("user_id"))
                .build();
    }
}
