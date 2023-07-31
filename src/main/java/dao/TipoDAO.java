package dao;

import model.Raca;
import model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoDAO {

    public boolean inserirTipo(String nome){
        String sql = "INSERT INTO tipo (nome) " +
                "VALUES (?)";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean atualizarTipo(int id, String nome){
        String sql = "UPDATE tipo SET nome = ? WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setInt(2, id);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deletarTipo(int id){
        String sql = "DELETE FROM raca WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Tipo getTipo(int idTipo){
        String sql = "SELECT * FROM raca WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idTipo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");

                    return new Tipo(id, nome);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Tipo> getTipos(){
        ArrayList<Tipo> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");

                tipos.add(new Tipo(id, nome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipos;
    }
}
