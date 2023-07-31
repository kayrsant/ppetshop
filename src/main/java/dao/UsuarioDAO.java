package dao;

import model.Usuario;
import org.apache.commons.codec.digest.Sha2Crypt;
import service.CadastrarService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario salvar(String nome, String cpf, String email, String dataCadastro, String login, String senha, String permissao) {
        Usuario c = new Usuario();

        String sql = "INSERT INTO usuario (nome, cpf, email, data_cadastro, login, senha, id_permissao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setString(3, email);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setString(5, login);
            statement.setString(6, senha);
            statement.setInt(7, 1);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public boolean deletar(Usuario c) {
        boolean deletado = false;

        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, c.getId());

            int rowsAffected = statement.executeUpdate();
            deletado = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deletado;
    }

    public boolean atualizar(String nome, String cpf, String email, String login, String senha, String permissao, int id) {
        boolean atualizado = false;

        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, login = ?, senha = ?, id_permissao = ? WHERE id = ?\n";

        try (Connection connection = new ConectaDB().getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            CadastrarService cs = new CadastrarService();

            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setString(3, email);
            statement.setString(4, login);
            senha = cs.criptografarSenha(senha);
            statement.setString(5, senha);
            statement.setInt(6, Integer.parseInt(permissao));
            statement.setInt(7, id);


            int rowsAffected = statement.executeUpdate();
            atualizado = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atualizado;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT nome, cpf, id_permissao FROM usuario";

        try (Connection connection = new ConectaDB().getConexao();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String permissao = resultSet.getString("id_permissao");

                Usuario usuario = new Usuario(nome, cpf, permissao);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario getUsuario(String login){

        Usuario c = new Usuario();

        try(Connection con = new ConectaDB().getConexao()){

            String sql = "SELECT * " +
                    "FROM usuario WHERE login = ?";

            PreparedStatement pt = con.prepareStatement(sql);
            pt.setString(1, login);
            System.out.println("sql: "+pt.toString());
            ResultSet rs = pt.executeQuery();

            while (rs.next()){
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setLogin(rs.getString("login"));
                c.setSenha(rs.getString("senha"));
                c.setPermissao(rs.getInt("id_permissao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public Usuario getUsuarioPorId(int id){
        Usuario c = new Usuario();

        try(Connection con = new ConectaDB().getConexao()){

            String sql = "SELECT * " +
                    "FROM usuario WHERE id = ?";

            PreparedStatement pt = con.prepareStatement(sql);
            pt.setInt(1, id);
            System.out.println("sql: "+pt.toString());
            ResultSet rs = pt.executeQuery();

            while (rs.next()){
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setLogin(rs.getString("login"));
                c.setSenha(rs.getString("senha"));
                c.setPermissao(rs.getInt("id_permissao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
}
