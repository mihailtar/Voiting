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


@WebServlet(name = "deleteTheme", urlPatterns = {"/deleteTheme", "/admin/deleteTheme"})
public class ThemeDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int themeId = Integer.parseInt(req.getParameter("themeId"));
            Theme theme = new Theme();
            theme.setId(themeId);
            Connection connection = DatabaseConnect.getConnection();
            ThemeService themeService = new ThemeService(connection);
            String result = themeService.deleteTheme(theme);
            if (result.equals("Success")) {
                req.setAttribute("themes", themeService.getThemeList());
                RequestDispatcher view = req.getRequestDispatcher("themes.jsp");
                view.forward(req, resp);
            } else {
                req.setAttribute("message", result);
                RequestDispatcher view = req.getRequestDispatcher("error.jsp");
                view.forward(req, resp);
            }
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - deleteTheme - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
    }
}
