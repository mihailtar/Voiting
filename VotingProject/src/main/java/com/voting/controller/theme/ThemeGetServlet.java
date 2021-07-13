package com.voting.controller.theme;

import com.voting.model.Theme;
import com.voting.service.ThemeService;
import com.voting.util.DatabaseConnect;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;


@WebServlet(name = "getTheme", urlPatterns = {"/getTheme", "/admin/getTheme"})
public class ThemeGetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int themeId = Integer.parseInt(req.getParameter("themeId"));
            Connection connection = DatabaseConnect.getConnection();
            ThemeService themeService = new ThemeService(connection);
            Theme theme = themeService.getTheme(themeId);
            if (theme != null) {
                req.setAttribute("theme", theme);
                RequestDispatcher view = req.getRequestDispatcher("theme.jsp");
                view.forward(req, resp);
            } else {
                req.setAttribute("message", "Theme not found");
                RequestDispatcher view = req.getRequestDispatcher("error.jsp");
                view.forward(req, resp);
            }
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getTheme - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
    }
}
