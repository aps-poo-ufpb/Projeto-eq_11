package com.petshop.service;

import com.petshop.model.Pet;
import com.petshop.repository.PetRepository;

import java.sql.SQLException;
import java.util.List;

public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Pet pet) throws SQLException {
        validar(pet);
        repository.salvar(pet);
    }

    public List<Pet> listarTodos() throws SQLException {
        return repository.listarTodos();
    }

    public Pet buscarPorId(int id) throws SQLException {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Pet não encontrado: " + id));
    }

    public void editar(Pet pet) throws SQLException {
        validar(pet);
        buscarPorId(pet.getId());
        repository.atualizar(pet);
    }

    public void remover(int id) throws SQLException {
        buscarPorId(id);
        repository.remover(id);
    }

    private void validar(Pet pet) {
        if (pet.getNome() == null || pet.getNome().isBlank())
            throw new IllegalArgumentException("Nome é obrigatório.");
        if (pet.getNome().length() < 2 || pet.getNome().length() > 100)
            throw new IllegalArgumentException("Nome deve ter entre 2 e 100 caracteres.");
        if (pet.getEspecie() == null || pet.getEspecie().isBlank())
            throw new IllegalArgumentException("Espécie é obrigatória.");
        if (pet.getIdade() < 0 || pet.getIdade() > 50)
            throw new IllegalArgumentException("Idade inválida.");
        if (pet.getDono() == null || pet.getDono().isBlank())
            throw new IllegalArgumentException("Nome do dono é obrigatório.");
    }
}
