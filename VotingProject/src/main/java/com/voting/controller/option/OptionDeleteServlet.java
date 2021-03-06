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


@WebServlet(name = "deleteOption", urlPatterns = {"/deleteOption", "/admin/deleteOption"})
public class OptionDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int optionId = Integer.parseInt(req.getParameter("optionId"));
            int themeId = Integer.parseInt(req.getParameter("themeId"));
            Option option = new Option();
            option.setId(optionId);
            option.setThemeId(themeId);
            Connection connection = DatabaseConnect.getConnection();
            OptionService optionService = new OptionService(connection);
            String result = optionService.deleteOption(option);
            if (result.equals("Success")) {
                System.out.println("themeId: " + themeId);
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
            System.err.println("ERROR - deleteOption - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
    }
}
