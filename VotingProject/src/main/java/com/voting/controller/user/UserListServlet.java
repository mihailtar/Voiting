package com.voting.controller.user;

import com.voting.service.UserService;
import com.voting.util.DatabaseConnect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "getUsers", urlPatterns = "/admin/getUsers")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = DatabaseConnect.getConnection();
        UserService userService = new UserService(connection);
        List users = userService.getUserList();
        req.setAttribute("users", users);
        RequestDispatcher view = req.getRequestDispatcher("users.jsp");
        view.forward(req, resp);
    }
}
