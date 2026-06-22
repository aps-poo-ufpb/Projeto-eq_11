package com.petshop.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    private static HikariDataSource dataSource;

    public static void init() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(System.getenv("DB_URL"));
        config.setUsername(System.getenv("DB_USER"));
        config.setPassword(System.getenv("DB_PASSWORD"));
        config.setMaximumPoolSize(5);

        dataSource = new HikariDataSource(config);
        logger.info("Banco de dados conectado.");
        runMigrations();
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static void runMigrations() {
        String sql = """
            CREATE TABLE IF NOT EXISTS pets (
                id        SERIAL PRIMARY KEY,
                nome      VARCHAR(100) NOT NULL,
                especie   VARCHAR(50)  NOT NULL,
                raca      VARCHAR(50),
                idade     INT          NOT NULL,
                dono      VARCHAR(100) NOT NULL,
                criado_em TIMESTAMP DEFAULT NOW()
            );
        """;
        try (var conn = getConnection(); var stmt = conn.createStatement()) {
            stmt.execute(sql);
            logger.info("Migrations executadas.");
        } catch (SQLException e) {
            logger.error("Erro nas migrations: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
