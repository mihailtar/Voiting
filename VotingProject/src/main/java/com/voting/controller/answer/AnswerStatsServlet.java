package com.voting.controller.answer;

import com.voting.model.Answer;
import com.voting.model.Statistic;
import com.voting.service.AnswerService;
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

@WebServlet(name = "getStats", urlPatterns = {"/getStats", "/user/getStats"})
public class AnswerStatsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int themeId = Integer.parseInt(req.getParameter("themeId"));
        String title = req.getParameter("title");

        Answer answer = new Answer();
        answer.setThemeId(themeId);

        Connection connection = DatabaseConnect.getConnection();
        AnswerService answerService = new AnswerService(connection);
        List<Statistic> result = answerService.getStats(themeId);

        req.setAttribute("stats", result);
        req.setAttribute("title", title);
        RequestDispatcher view = req.getRequestDispatcher("stats.jsp");
        view.forward(req, resp);

    }
}
