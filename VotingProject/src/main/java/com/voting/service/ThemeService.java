package com.voting.service;

import com.voting.model.Theme;
import com.voting.model.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThemeService {

    private Connection connection;

    public ThemeService(Connection c) {
        this.connection = c;
    }

    public List getThemeList() {
        List<Theme> themeList = new ArrayList<Theme>();
        try {
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select id, title, active from themes order by title");
            while (rs.next()) {
                Theme theme = new Theme();
                theme.setId(rs.getInt("id"));
                theme.setTitle(rs.getString("title"));
                theme.setActive(rs.getBoolean("active"));
                themeList.add(theme);
            }
            rs.close();
            state.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getThemeList - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return themeList;
    }

    public List getActiveThemeListByUser(int userId) {
        List<Theme> themeList = new ArrayList<Theme>();
        try {
            PreparedStatement pst = connection.prepareStatement("select id, title from themes where active and id not in (select theme_id from answers where user_id = ?) order by title");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Theme theme = new Theme();
                theme.setId(rs.getInt("id"));
                theme.setTitle(rs.getString("title"));
                themeList.add(theme);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getThemeList - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return themeList;
    }

    public List getOldThemeListByUser(int userId) {
        List<Theme> themeList = new ArrayList<Theme>();
        try {
            PreparedStatement pst = connection.prepareStatement("select id, title from themes " +
                    "where active and id in (select theme_id from answers where user_id = ?) order by title");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Theme theme = new Theme();
                theme.setId(rs.getInt("id"));
                theme.setTitle(rs.getString("title"));
                themeList.add(theme);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getThemeList - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return themeList;
    }

    public Theme getTheme(int id) {
        Theme theme = null;
        try {
            PreparedStatement pst = connection.prepareStatement("select * from themes where id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                theme = new Theme();
                theme.setId(rs.getInt("id"));
                theme.setTitle(rs.getString("title"));
                theme.setActive(rs.getBoolean("active"));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - getTheme - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
        return theme;
    }

    public String addTheme(Theme theme) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("insert into themes(title, active) values (?,?)");
            pst.setString(1, theme.getTitle());
            pst.setBoolean(2, theme.isActive());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - addTheme - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String editTheme(Theme theme) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("update themes set title=?, active=? where id = ?");
            pst.setString(1, theme.getTitle());
            pst.setBoolean(2, theme.isActive());
            pst.setInt(3, theme.getId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - editTheme - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String deleteTheme(Theme theme) {
        String result;
        try {
            PreparedStatement pst = connection.prepareStatement("delete from themes where id = ?");
            pst.setInt(1, theme.getId());
            pst.executeUpdate();
            result = "Success";
            pst.close();
        } catch (Exception e) {
            System.err.println("----------------");
            System.err.println("ERROR - deleteTheme - " + e.getLocalizedMessage());
            System.err.println("----------------");
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
