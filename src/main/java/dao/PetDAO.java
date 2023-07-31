package dao;

import model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    public boolean salvar(Pet p) {
        boolean salvou = false;

        String sql = "INSERT INTO pet (id_raca, id_tipo, id_usuario, nome, idade) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, p.getIdRaca());
            statement.setInt(2, p.getIdTipo());
            statement.setInt(3, p.getIdUsuario());
            statement.setString(4, p.getNome());
            statement.setInt(5, p.getIdade());

            int rowsAffected = statement.executeUpdate();
            salvou = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salvou;
    }

    public boolean deletar(int id) {
        boolean deletado = false;

        String sql = "DELETE FROM pet WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            deletado = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deletado;
    }

    public boolean atualizar(String idRaca, String idTipo, String nome, String idade, String id) {
        boolean atualizado = false;

        String sql = "UPDATE pet SET id_raca = ?, id_tipo = ?, nome = ?, idade = ? WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, Integer.parseInt(idRaca));
            statement.setInt(2, Integer.parseInt(idTipo));
            statement.setString(3, nome);
            statement.setInt(4, Integer.parseInt(idade));
            statement.setInt(5, Integer.parseInt(id));

            int rowsAffected = statement.executeUpdate();
            atualizado = rowsAffected > 0;

            System.out.println("ID: " + id + " ID_RACA: " + idRaca + " ID_TIPO: " + idTipo + " NOME: " + nome + " IDADE: " + idade);

            // Adicione prints para depuração
            System.out.println("Número de linhas afetadas: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            // Adicione prints para depuração
            System.err.println("Erro ao atualizar o pet: " + e.getMessage());
        }

        return atualizado;
    }


    public Pet getPet(int idUsuario, int idPet) {
        Pet pet = null;

        String sql = "SELECT id, id_raca, id_tipo, nome, idade FROM pet WHERE id_usuario = ? AND id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.setInt(2, idPet);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int idRaca = resultSet.getInt("id_raca");
                    int idTipo = resultSet.getInt("id_tipo");
                    String nome = resultSet.getString("nome");
                    int idade = resultSet.getInt("idade");

                    pet = new Pet(id, idRaca, idTipo, idUsuario, nome, idade);
                    System.out.println(pet.getId() + " " + pet.getNome());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pet;
    }

    public ArrayList<Pet> getPets(int idUsuario) {
        ArrayList<Pet> pets = new ArrayList<>();

        String sql = "SELECT id, id_raca, id_tipo, nome, idade FROM pet WHERE id_usuario = ?";


        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int idRaca = resultSet.getInt("id_raca");
                    int idTipo = resultSet.getInt("id_tipo");
                    String nome = resultSet.getString("nome");
                    int idade = resultSet.getInt("idade");

                    Pet pet = new Pet(id, idRaca, idTipo, idUsuario, nome, idade);
                    pets.add(pet);
                    System.out.println(pet.getId() + " " + pet.getNome());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }
}
