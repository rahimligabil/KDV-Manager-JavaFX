package com.gabil.kdvapp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SingletonDBConnection {

    private static SingletonDBConnection instance;
    private Connection connection;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;

    // Constructor
    private SingletonDBConnection() {
        try {
            loadConfig();
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Connect successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Error in database connection!");
        }
    }

    private void loadConfig() throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        props.load(fis);

        DRIVER = props.getProperty("db.driver");
        URL = props.getProperty("db.url");
        USERNAME = props.getProperty("db.username");
        PASSWORD = props.getProperty("db.password");
    }

    public static synchronized SingletonDBConnection getInstance() {
        if (instance == null) {
            instance = new SingletonDBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void closeConnection() {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
                System.out.println("🔒 Connection lose.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
