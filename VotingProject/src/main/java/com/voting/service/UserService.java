package com.voting.service;

import com.voting.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private Connection connection;

    public UserService(Connection c) {
        this.connection = c;
    }

    public List getUserList() {
        List<User> userList = new ArrayList<User>();
        try {
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select id, login from user order by login");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                userList.add(user);
            }
            rs.close();
            state.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getUserList - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return userList;
    }

    public String addUser(User user) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("insert into user(login,password,salt,role) values (?,?,?,?)");
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getSalt());
            pst.setString(4, user.getRole());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - addUser - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public User getUser(String login) {
        User user = null;
        try {
            PreparedStatement pst = connection.prepareStatement("select * from user where login=?");
            pst.setString(1, login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setSalt(rs.getString("salt"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getUser - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return user;
    }

    public String editUser(User user) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("update user set login=?, password=?, salt=?, role=? where id = ?");
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getSalt());
            pst.setString(4, user.getRole());
            pst.setInt(5, user.getId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - editUser - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String deleteUser(User user) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("delete from user where id = ?");
            pst.setInt(1, user.getId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - deleteUser - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
