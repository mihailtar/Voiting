package com.voting.controller.option;

import com.voting.model.Option;
import com.voting.service.OptionService;
import com.voting.util.DatabaseConnect;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;


@WebServlet(name = "addOption", urlPatterns = {"/addOption", "/admin/addOption"})
public class OptionAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String title = req.getParameter("title");
            int themeId = Integer.parseInt(req.getParameter("themeId"));
            Option option = new Option();
            option.setTitle(title);
            option.setThemeId(themeId);
            Connection connection = DatabaseConnect.getConnection();
            OptionService optionService = new OptionService(connection);
            String result = optionService.addOption(option);
            if (result.equals("Success")) {
                req.setAttribute("options", optionService.getOptionListByTheme(themeId));
                req.setAttribute("themeId", themeId);
                RequestDispatcher view = req.getRequestDispatcher("options.jsp");
                view.forward(req, resp);
            } else {
                req.setAttribute("message", result);
                RequestDispatcher view = req.getRequestDispatcher("error.jsp");
                view.forward(req, resp);
            }
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - addOption - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
    }
}
