package dao;

import model.Raca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RacaDAO {

    public boolean inserirRaca(int idTipo, String nome){

        String sql = "INSERT INTO raca (id_tipo, nome) " +
                "VALUES (?, ?)";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idTipo);
            statement.setString(2, nome);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean atualizarRaca(int idRaca, int idTipo, String nome) {
        String sql = "UPDATE raca SET id_tipo = ?, nome = ? WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idTipo);
            statement.setString(2, nome);
            statement.setInt(3, idRaca);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deletarRaca(int idRaca) {
        String sql = "DELETE FROM raca WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idRaca);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Raca getRaca(int idRaca) {
        String sql = "SELECT * FROM raca WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idRaca);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int idTipo = resultSet.getInt("id_tipo");
                    String nome = resultSet.getString("nome");

                    return new Raca(idTipo, nome);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Raca> getRacas() {
        ArrayList<Raca> racas = new ArrayList<>();
        String sql = "SELECT * FROM raca";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int idTipo = resultSet.getInt("id_tipo");
                String nome = resultSet.getString("nome");

                racas.add(new Raca(id, idTipo, nome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return racas;
    }
}