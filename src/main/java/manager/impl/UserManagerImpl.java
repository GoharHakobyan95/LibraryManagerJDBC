package com.epam.library.manager.impl;

import com.epam.library.db.DBConnectionProvider;
import com.epam.library.manager.UserManager;
import com.epam.library.model.User;
import com.epam.library.model.UserRole;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManagerImpl implements UserManager<Integer, User> {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    @Override
    public User getById(Integer id) {
        String sql = "SELECT * FROM user  WHERE id = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getUserFromResulSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM  user where user_role = 'USER'";
        List<User> users = new LinkedList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()) {
                users.add(getUserFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(name, last_name, email, password, user_role) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, UserRole.USER.toString());
            int execute = preparedStatement.executeUpdate();
            System.out.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "Update user set name = ?,last_name = ?,email = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM user WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user where email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return getUserFromResulSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Given user with email: %s and password %s not found", email, password));
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getUserFromResulSet(rs);
            } else {
                System.out.println("No user found with email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeById(Integer userId) {
        String sql = "delete from user where id = " + userId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResulSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .userRole(UserRole.valueOf(resultSet.getString("user_role")))
                .build();
    }
}
