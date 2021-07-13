package com.voting.model;

import java.util.Date;

public class Answer {
    private int id;
    private int themeId;
    private String themeTitle;
    private int userId;
    private String userLogin;
    private int optionId;
    private String optionTitle;
    private Date dateVote;

    public Answer() {
    }

    public Answer(int id, int themeId, String themeTitle, int userId, String userLogin, int optionId, String optionTitle, Date dateVote) {
        this.id = id;
        this.themeId = themeId;
        this.themeTitle = themeTitle;
        this.userId = userId;
        this.userLogin = userLogin;
        this.optionId = optionId;
        this.optionTitle = optionTitle;
        this.dateVote = dateVote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public Date getDateVote() {
        return dateVote;
    }

    public void setDateVote(Date dateVote) {
        this.dateVote = dateVote;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }
}
