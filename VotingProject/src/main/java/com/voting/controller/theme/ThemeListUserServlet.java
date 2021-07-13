package com.voting.controller.theme;

import com.voting.service.ThemeService;
import com.voting.util.DatabaseConnect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "getUserThemes", urlPatterns = "/user/getUserThemes")
public class ThemeListUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        System.out.println(userId);
        Connection connection = DatabaseConnect.getConnection();
        ThemeService themeService = new ThemeService(connection);
        List themes = themeService.getActiveThemeListByUser(userId);
        req.setAttribute("themes", themes);
        RequestDispatcher view = req.getRequestDispatcher("themes.jsp");
        view.forward(req, resp);
    }
}
