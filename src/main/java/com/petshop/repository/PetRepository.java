package com.petshop.repository;

import com.petshop.config.DatabaseConfig;
import com.petshop.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetRepository {

    public void salvar(Pet pet) throws SQLException {
        String sql = "INSERT INTO pets (nome, especie, raca, idade, dono) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getEspecie());
            stmt.setString(3, pet.getRaca());
            stmt.setInt(4, pet.getIdade());
            stmt.setString(5, pet.getDono());
            stmt.executeUpdate();
        }
    }

    public List<Pet> listarTodos() throws SQLException {
        String sql = "SELECT * FROM pets ORDER BY nome";
        List<Pet> lista = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Optional<Pet> buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pets WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapear(rs));
            }
        }
        return Optional.empty();
    }

    public void atualizar(Pet pet) throws SQLException {
        String sql = "UPDATE pets SET nome=?, especie=?, raca=?, idade=?, dono=? WHERE id=?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getEspecie());
            stmt.setString(3, pet.getRaca());
            stmt.setInt(4, pet.getIdade());
            stmt.setString(5, pet.getDono());
            stmt.setInt(6, pet.getId());
            stmt.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Pet mapear(ResultSet rs) throws SQLException {
        return new Pet(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("especie"),
            rs.getString("raca"),
            rs.getInt("idade"),
            rs.getString("dono")
        );
    }
}
