package com.petshop;

import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Iniciando PetShop...");


        var resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");

        var engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);


        var app = Javalin.create();
        app.get("/ping", ctx -> ctx.json(java.util.Map.of("status", "ok")));


        app.get("/", ctx -> {
            logger.info("GET /");
            var context = new Context();
            context.setVariable("mensagem", "PetShop!");
            ctx.html(engine.process("index", context));
        });


        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        app.start(port);

        logger.info("PetShop iniciado na porta {}", port);
    }
}
