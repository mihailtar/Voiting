package com.voting.service;

import com.voting.model.Answer;
import com.voting.model.Statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnswerService {

    private Connection connection;

    public AnswerService(Connection c) {
        this.connection = c;
    }

    public List getAnswerListByTheme(int themeId) {
        List<Answer> answerList = new ArrayList<Answer>();
        try {
            PreparedStatement pst = connection.prepareStatement("select a.id, u.login, o.id as optionId, o.title as options, a.date_vote " +
                    " from answers a, user u, option o " +
                    " where a.theme_id = ? and a.user_id = u.id " +
                    " and o.id = a.option_id " +
                    " order by a.date_vote");
            pst.setInt(1, themeId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setOptionId(rs.getInt("optionId"));
                answer.setOptionTitle(rs.getString("options"));
                answer.setUserLogin(rs.getString("login"));
                answer.setDateVote(rs.getDate("date_vote"));
                answerList.add(answer);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getAnswerListByTheme - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return answerList;
    }

    public List getStats(int themeId) {
        List<Statistic> statisticList = new ArrayList<Statistic>();
        try {
            PreparedStatement pst = connection.prepareStatement(
                    "select o.title, count(a.id) as count" +
                            " from answers a, options o " +
                            " where a.theme_id = ? " +
                            " and o.id = a.option_id " +
                            " group by o.id " +
                            " order by 2 desc");
            pst.setInt(1, themeId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Statistic statistic = new Statistic();
                statistic.setOption(rs.getString("title"));
                statistic.setCount(rs.getInt("count"));
                statisticList.add(statistic);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getStats - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return statisticList;
    }

    public List getAnswerListByUser(int userId) {
        List<Answer> answerList = new ArrayList<Answer>();
        try {
            PreparedStatement pst = connection.prepareStatement("select a.id, u.login, t.title as theme, a.theme_id, o.id as optionId, o.title as options, a.date_vote " +
                    " from answers a, user u, option o, theme t " +
                    " where a.user_id = ? and a.user_id = u.id " +
                    " and o.id = a.option_id and a.theme_id = t.id " +
                    " order by a.date_vote");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setOptionId(rs.getInt("optionId"));
                answer.setOptionTitle(rs.getString("options"));
                answer.setThemeId(rs.getInt("theme_id"));
                answer.setThemeTitle(rs.getString("theme"));
                answer.setUserLogin(rs.getString("login"));
                answer.setDateVote(rs.getDate("date_vote"));
                answerList.add(answer);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getAnswerListByUser - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return answerList;
    }

    public Answer getAnswer(int id) {
        Answer answer = null;
        try {
            PreparedStatement pst = connection.prepareStatement("select a.id, u.login, o.id as optionId, o.title as options, a.date_vote " +
                    " from answers a, user u, option o " +
                    " where a.id = ? and a.user_id = u.id " +
                    " and o.id = a.option_id " +
                    " order by a.date_vote");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setOptionId(rs.getInt("optionId"));
                answer.setOptionTitle(rs.getString("options"));
                answer.setUserLogin(rs.getString("login"));
                answer.setDateVote(rs.getDate("date_vote"));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getAnswer - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return answer;
    }

    public String addAnswer(Answer answer) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("insert into answers(theme_id, user_id, option_id) values (?,?,?)");
            pst.setInt(1, answer.getThemeId());
            pst.setInt(2, answer.getUserId());
            pst.setInt(3, answer.getOptionId());
            pst.executeUpdate();
            pst.close();
            result = "Success";
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - addAnswer - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String editAnswer(Answer answer) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("update answers set theme_id=?, user_id=?, option_id=? where id = ?");
            pst.setInt(1, answer.getThemeId());
            pst.setInt(2, answer.getUserId());
            pst.setInt(3, answer.getOptionId());
            pst.setInt(4, answer.getId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - editAnswer - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String deleteAnswer(Answer answer) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("delete from answers where id = ?");
            pst.setInt(1, answer.getId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - deleteAnswer - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
