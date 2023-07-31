package dao;

import model.Agendamento;
import model.Servico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    public boolean inserirServico(String nome, String descricao, double duracao) {
        String sql = "INSERT INTO servico (nome, descricao, duracao) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, descricao);
            statement.setDouble(3, duracao);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void deletarServico(int id) {
        String sql = "DELETE FROM servico WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarServico(Servico servico) {
        String sql = "UPDATE servico SET nome = ?, descricao = ?, duracao = ?" +
                "WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, servico.getNome());
            statement.setString(2, servico.getDescricao());
            statement.setDouble(3, servico.getDuracao());
            statement.setInt(4, servico.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Servico buscarServicoPorId(int id) {
        String sql = "SELECT * FROM servico WHERE id = ?";
        Servico servico = null;

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                servico = new Servico();
                servico.setId(resultSet.getInt("id"));
                servico.setNome(resultSet.getString("nome"));
                servico.setDescricao(resultSet.getString("descricao"));
                servico.setDuracao(resultSet.getDouble("duracao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servico;
    }

    public ArrayList<Servico> buscarTodosServicoPorIdUsuario(int id) {
        String sql = "SELECT * FROM servico WHERE id = ?";
        ArrayList<Servico> servicos = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Servico servico = new Servico();
                servico.setId(resultSet.getInt("id"));
                servico.setNome(resultSet.getString("nome"));
                servico.setDescricao(resultSet.getString("descricao"));
                servico.setDuracao(resultSet.getDouble("duracao"));

                servicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicos;
    }

    public ArrayList<Servico> buscarTodosServicos() {
        String sql = "SELECT * FROM servico";
        ArrayList<Servico> todosServicos = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Servico servico = new Servico();
                servico.setId(resultSet.getInt("id"));
                servico.setNome(resultSet.getString("nome"));
                servico.setDescricao(resultSet.getString("descricao"));
                servico.setDuracao(resultSet.getDouble("duracao"));

                todosServicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todosServicos;
    }
}
