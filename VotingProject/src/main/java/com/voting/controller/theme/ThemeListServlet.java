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

@WebServlet(name = "getThemes", urlPatterns = "/admin/getThemes")
public class ThemeListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = DatabaseConnect.getConnection();
        ThemeService themeService = new ThemeService(connection);
        List themes = themeService.getThemeList();
        req.setAttribute("themes", themes);
        RequestDispatcher view = req.getRequestDispatcher("themes.jsp");
        view.forward(req, resp);
    }
}
