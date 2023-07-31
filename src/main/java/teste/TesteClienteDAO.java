package teste;

import dao.UsuarioDAO;
import model.Usuario;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TesteClienteDAO {

    public static void main(String[] args) {
        // Criar instância do ClienteDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        /*
        // Testar o método salvar
        Usuario novoUsuario = usuarioDAO.salvar( "João", "123456789", "joao@example.com", String.valueOf(LocalDate.now()), "joao123", "senha123", "1");
        System.out.println("Cliente salvo: " + novoUsuario != null);*/

        // Testar o método getClientes
        List<Usuario> usuarios = usuarioDAO.getUsuarios();
        System.out.println("Lista de clientes:");
        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Permissão: " + usuario.getPermissao());
        }
        /*
        // Testar o método atualizar
        Usuario usuarioExistente = new Usuario(4, "Maria", "987654321", "maria@example.com", Date.valueOf(LocalDate.now()), "maria123", "novasenha");
        boolean atualizado = usuarioDAO.atualizar(usuarioExistente);
        System.out.println("Cliente atualizado: " + atualizado); */

        // Testar o método deletar
        Usuario usuarioParaDeletar = new Usuario(3); // Supondo que o cliente com ID 3 exista
        boolean deletado = usuarioDAO.deletar(usuarioParaDeletar);
        System.out.println("Cliente deletado: " + deletado);
    }
}
