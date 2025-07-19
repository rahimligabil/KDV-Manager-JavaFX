package com.gabil.kdvapp.config;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {

    public static void initDatabase() {
        try {
            Connection conn = SingletonDBConnection.getInstance().getConnection();

            Statement stmt = conn.createStatement();

            // USERS tablosu
            String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    email TEXT NOT NULL,
                    password TEXT NOT NULL
                );
            """;

            // KDV tablosu
            String createKdvTable = """
                CREATE TABLE IF NOT EXISTS kdvs (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    rate REAL NOT NULL,
                    description TEXT
                );
            """;

            // NOTES tablosu
            String createNotesTable = """
                CREATE TABLE IF NOT EXISTS notes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    content TEXT,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );
            """;

            // Sorguları sırayla çalıştır
            stmt.execute(createUsersTable);
            stmt.execute(createKdvTable);
            stmt.execute(createNotesTable);

            System.out.println("✅ Database initialized successfully.");

        } catch (SQLException e) {
            System.err.println("❌ Database init failed: " + e.getMessage());
        }
    }
}
