package com.petshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final TemplateEngine engine;

    public HomeController(TemplateEngine engine) {
        this.engine = engine;
    }

    public void index(io.javalin.http.Context ctx) {
        logger.info("GET /");
        var context = new Context();
        context.setVariable("titulo", "PetShop");
        ctx.html(engine.process("index", context));
    }
}
