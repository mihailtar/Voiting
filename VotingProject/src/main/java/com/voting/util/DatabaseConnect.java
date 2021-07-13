package com.voting.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    // это url нашей базы данных , к которой мы хотим подключиться
                    "jdbc:mysql://localhost:3306/voting",
                    // тут указываем данные для авторизации
                    "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
