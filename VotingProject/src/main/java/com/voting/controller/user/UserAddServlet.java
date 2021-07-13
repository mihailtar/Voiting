package com.voting.controller.user;

import com.voting.model.User;
import com.voting.service.UserService;
import com.voting.util.DatabaseConnect;
import com.voting.util.PasswordEncryption;

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


@WebServlet(name = "newUser", urlPatterns = "/newUser")
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            User user = new User();
            user.setLogin(login);
            user.setSalt(PasswordEncryption.generateSalt());
            user.setPassword(PasswordEncryption.hashPassword(password, user.getSalt()));
            user.setRole(role);
            Connection connection = DatabaseConnect.getConnection();
            UserService userService = new UserService(connection);
            String result = userService.addUser(user);
            req.setAttribute("result", result);
            RequestDispatcher view = req.getRequestDispatcher("registration.jsp");
            view.forward(req, resp);
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - newUser - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
    }
}
