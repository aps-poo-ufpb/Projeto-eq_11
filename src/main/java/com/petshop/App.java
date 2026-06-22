package com.petshop;

import com.petshop.config.DatabaseConfig;
import com.petshop.config.RouteConfig;
import com.petshop.config.ThymeleafConfig;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Iniciando PetShop...");

        DatabaseConfig.init();

        var engine = ThymeleafConfig.buildEngine();

        var app = Javalin.create();

        RouteConfig.register(app, engine);

        int port = Integer.parseInt(
            System.getenv().getOrDefault("PORT", "8080")
        );
        app.start(port);

        logger.info("PetShop iniciado na porta {}", port);
    }
}
