package com.petshop.controller;

import com.petshop.model.Pet;
import com.petshop.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);
    private final PetService service;
    private final TemplateEngine engine;

    public PetController(PetService service, TemplateEngine engine) {
        this.service = service;
        this.engine = engine;
    }

    public void listar(io.javalin.http.Context ctx) throws Exception {
        logger.info("GET /pets");
        var context = new Context();
        context.setVariable("pets", service.listarTodos());
        context.setVariable("sucesso", ctx.queryParam("sucesso"));
        ctx.html(engine.process("pet/lista", context));
    }

    public void formularioCadastro(io.javalin.http.Context ctx) {
        var context = new Context();
        context.setVariable("pet", new Pet());
        ctx.html(engine.process("pet/formulario", context));
    }

    public void cadastrar(io.javalin.http.Context ctx) throws Exception {
        var pet = extrairFormulario(ctx, 0);
        try {
            service.cadastrar(pet);
            logger.info("Pet cadastrado: {}", pet.getNome());
            ctx.redirect("/pets?sucesso=Pet cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            var context = new Context();
            context.setVariable("pet", pet);
            context.setVariable("erro", e.getMessage());
            ctx.html(engine.process("pet/formulario", context));
        }
    }

    public void formularioEdicao(io.javalin.http.Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        var pet = service.buscarPorId(id);
        var context = new Context();
        context.setVariable("pet", pet);
        ctx.html(engine.process("pet/formulario", context));
    }

    public void editar(io.javalin.http.Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        var pet = extrairFormulario(ctx, id);
        try {
            service.editar(pet);
            logger.info("Pet editado: id={}", id);
            ctx.redirect("/pets?sucesso=Pet atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            var context = new Context();
            context.setVariable("pet", pet);
            context.setVariable("erro", e.getMessage());
            ctx.html(engine.process("pet/formulario", context));
        }
    }

    public void remover(io.javalin.http.Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.remover(id);
        logger.info("Pet removido: id={}", id);
        ctx.redirect("/pets?sucesso=Pet removido com sucesso!");
    }

    private Pet extrairFormulario(io.javalin.http.Context ctx, int id) {
        var pet = new Pet();
        pet.setId(id);
        pet.setNome(ctx.formParam("nome"));
        pet.setEspecie(ctx.formParam("especie"));
        pet.setRaca(ctx.formParam("raca"));
        try {
            pet.setIdade(Integer.parseInt(ctx.formParam("idade")));
        } catch (NumberFormatException e) {
            pet.setIdade(0);
        }
        pet.setDono(ctx.formParam("dono"));
        return pet;
    }
}
