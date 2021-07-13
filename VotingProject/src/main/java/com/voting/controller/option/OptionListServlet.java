package com.voting.controller.option;

import com.voting.service.OptionService;
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

@WebServlet(name = "getOptions", urlPatterns = "/admin/getOptions")
public class OptionListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int themeId = Integer.parseInt(req.getParameter("themeId"));
        Connection connection = DatabaseConnect.getConnection();
        OptionService optionService = new OptionService(connection);
        List options = optionService.getOptionListByTheme(themeId);
        req.setAttribute("options", options);
        req.setAttribute("themeId", themeId);
        RequestDispatcher view = req.getRequestDispatcher("options.jsp");
        view.forward(req, resp);
    }
}
