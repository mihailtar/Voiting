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


@WebServlet(name = "getOption", urlPatterns = {"/getOption", "/admin/getOption"})
public class OptionGetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int themeId = Integer.parseInt(req.getParameter("themeId"));
            int optionId = Integer.parseInt(req.getParameter("optionId"));
            Connection connection = DatabaseConnect.getConnection();
            OptionService themeService = new OptionService(connection);
            Option option = themeService.getOption(optionId);
            if (option != null) {
                req.setAttribute("option", option);
                req.setAttribute("themeId", themeId);
                RequestDispatcher view = req.getRequestDispatcher("option.jsp");
                view.forward(req, resp);
            } else {
                req.setAttribute("message", "Option not found");
                RequestDispatcher view = req.getRequestDispatcher("error.jsp");
                view.forward(req, resp);
            }
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getOption - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
    }
}
