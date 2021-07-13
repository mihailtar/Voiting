package com.voting.service;

import com.voting.model.Option;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OptionService {

    private Connection connection;

    public OptionService(Connection c) {
        this.connection = c;
    }

    public List getOptionListByTheme(int themeId) {
        List<Option> optionList = new ArrayList<Option>();
        try {
            PreparedStatement pst = connection.prepareStatement("select id, title from options o where o.theme_id = ? order by title");
            pst.setInt(1, themeId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Option option = new Option();
                option.setId(rs.getInt("id"));
                option.setTitle(rs.getString("title"));
                optionList.add(option);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getOptionList - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return optionList;
    }

    public Option getOption(int id) {
        Option option = null;
        try {
            PreparedStatement pst = connection.prepareStatement("select o.id, o.title from options o where o.id = ? order by o.title");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                option = new Option();
                option.setId(rs.getInt("id"));
                option.setTitle(rs.getString("title"));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getOption - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return option;
    }

    public String addOption(Option option) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("insert into options(title, theme_id) values (?,?)");
            pst.setString(1, option.getTitle());
            pst.setInt(2, option.getThemeId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - addOption - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String editOption(Option option) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("update options set title=? where id = ?");
            pst.setString(1, option.getTitle());
            pst.setInt(2, option.getId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - editOption - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String deleteOption(Option option) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("delete from options where id = ?");
            pst.setInt(1, option.getId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - deleteOption - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
