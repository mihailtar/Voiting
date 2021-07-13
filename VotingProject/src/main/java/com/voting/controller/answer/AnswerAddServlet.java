package com.voting.controller.answer;

import com.voting.model.Answer;
import com.voting.model.Option;
import com.voting.service.AnswerService;
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

@WebServlet(name = "addAnswer", urlPatterns = {"/addAnswer", "/user/addAnswer"})
public class AnswerAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int themeId = Integer.parseInt(req.getParameter("themeId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        int optionId = Integer.parseInt(req.getParameter("optionId"));
        Answer answer = new Answer();
        answer.setThemeId(themeId);
        answer.setOptionId(optionId);
        answer.setUserId(userId);
        Connection connection = DatabaseConnect.getConnection();
        AnswerService answerService = new AnswerService(connection);
        String result = answerService.addAnswer(answer);
        if (result.equals("Success")) {
            req.setAttribute("result", "Thanks for voting");
            RequestDispatcher view = req.getRequestDispatcher("vote.jsp");
            view.forward(req, resp);
        } else {
            req.setAttribute("message", result);
            RequestDispatcher view = req.getRequestDispatcher("error.jsp");
            view.forward(req, resp);
        }
    }
}
