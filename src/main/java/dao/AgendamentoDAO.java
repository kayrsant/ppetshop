package dao;

import model.Agendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    public boolean inserirAgendamento(int idUsuario, String idPet, String idServico, Timestamp dataAgen) {
        String sql = "INSERT INTO agendamento (id_usuario, id_pet, id_servico, data_agendamento) " +
                "VALUES (?, ?, ?, ?)";

            try (Connection connection = new ConectaDB().getConexao();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.setInt(2, Integer.parseInt(idPet));
            statement.setInt(3, Integer.parseInt(idServico));
            statement.setTimestamp(4, dataAgen);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deletarAgendamento(int id) {
        String sql = "DELETE FROM agendamento WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean atualizarAgendamento(int idUsuario, String idPet, String idServico, Timestamp dataAgendamento, String id) {
        String sql = "UPDATE agendamento SET id_usuario = ?, id_pet = ?, id_servico = ?, data_agendamento = ? " +
                "WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            statement.setInt(2, Integer.parseInt(idPet));
            statement.setInt(3, Integer.parseInt(idServico));
            statement.setTimestamp(4, dataAgendamento);
            statement.setInt(5, Integer.parseInt(id));

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Agendamento buscarAgendamentoPorId(int id) {
        String sql = "SELECT * FROM agendamento WHERE id = ?";
        Agendamento agendamento = null;

        try (Connection connection = new ConectaDB().getConexao();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                agendamento = new Agendamento();
                agendamento.setId(resultSet.getInt("id"));
                agendamento.setIdUsuario(resultSet.getInt("id_usuario"));
                agendamento.setIdPet(resultSet.getInt("id_pet"));
                agendamento.setIdServico(resultSet.getInt("id_servico"));
                agendamento.setDataAgendamento(resultSet.getTimestamp("data_agendamento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agendamento;
    }

    public ArrayList<Agendamento> buscarTodosAgendamentosPorIdUsuario(int idUsuario) {
        String sql = "SELECT * FROM agendamento WHERE id_usuario = ?";
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setId(resultSet.getInt("id"));
                agendamento.setIdUsuario(resultSet.getInt("id_usuario"));
                agendamento.setIdPet(resultSet.getInt("id_pet"));
                agendamento.setIdServico(resultSet.getInt("id_servico"));
                agendamento.setDataAgendamento(resultSet.getTimestamp("data_agendamento"));

                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agendamentos;
    }

    public List<Agendamento> buscarTodosAgendamentos(){
        String sql = "SELECT * FROM agendamento";
        List<Agendamento> todosAgendamentos = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setId(resultSet.getInt("id"));
                agendamento.setIdUsuario(resultSet.getInt("id_usuario"));
                agendamento.setIdPet(resultSet.getInt("id_pet"));
                agendamento.setIdServico(resultSet.getInt("id_servico"));
                agendamento.setDataAgendamento(resultSet.getTimestamp("data_agendamento"));

                todosAgendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todosAgendamentos;
    }
}
