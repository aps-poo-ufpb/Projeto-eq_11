package com.petshop.config;

import com.petshop.controller.HomeController;
import com.petshop.controller.PetController;
import com.petshop.repository.PetRepository;
import com.petshop.service.PetService;
import io.javalin.Javalin;
import org.thymeleaf.TemplateEngine;

public class RouteConfig {

    public static void register(Javalin app, TemplateEngine engine) {

        // Health check — obrigatório para o portal do professor
        app.get("/ping", ctx -> ctx.json(java.util.Map.of("status", "ok")));

        // Página inicial
        var homeController = new HomeController(engine);
        app.get("/", homeController::index);

        // CRUD de Pets
        var petController = new PetController(
            new PetService(new PetRepository()), engine
        );
        app.get("/pets",               petController::listar);
        app.get("/pets/novo",          petController::formularioCadastro);
        app.post("/pets",              petController::cadastrar);
        app.get("/pets/{id}/editar",   petController::formularioEdicao);
        app.post("/pets/{id}/editar",  petController::editar);
        app.post("/pets/{id}/remover", petController::remover);
    }
}
