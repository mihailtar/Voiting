package com.voting.model;

public class User {
    private int id;
    private String login;
    private String salt;
    private String password;
    private String role;

    public User() {
    }

    public User(int id, String login, String salt, String password, String role) {
        this.id = id;
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
