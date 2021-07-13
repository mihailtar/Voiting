package com.voting.model;

public class Option {
    private int id;
    private String title;
    private int themeId;

    public Option() {
    }

    public Option(int id, String title, int themeId) {
        this.id = id;
        this.title = title;
        this.themeId = themeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

}
