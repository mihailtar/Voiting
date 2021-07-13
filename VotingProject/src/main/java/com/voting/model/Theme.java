package com.voting.model;

public class Theme {
    private int id;
    private String title;
    private boolean active;

    public Theme() {
    }

    public Theme(int id, String title, boolean active) {
        this.id = id;
        this.title = title;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
