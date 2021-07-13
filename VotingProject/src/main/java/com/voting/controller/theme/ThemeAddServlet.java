package com.voting.controller.theme;

import com.voting.model.Theme;
import com.voting.model.User;
import com.voting.service.ThemeService;
import com.voting.service.UserService;
import com.voting.util.DatabaseConnect;
import com.voting.util.PasswordEncryption;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;


@WebServlet(name = "addTheme", urlPatterns = {"/addTheme", "/admin/addTheme"})
public class ThemeAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String title = req.getParameter("title");
            Theme theme = new Theme();
            theme.setActive(true);
            theme.setTitle(title);
            Connection connection = DatabaseConnect.getConnection();
            ThemeService themeService = new ThemeService(connection);
            String result = themeService.addTheme(theme);
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
            System.err.println("ERROR - addTheme - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
    }
}
