package teste;

import dao.AgendamentoDAO;
import dao.PetDAO;
import dao.UsuarioDAO;
import model.Agendamento;
import model.Usuario;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TesteAgendamentoDAO {

    public static void main(String[] args) {

        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

        /*

        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(1);

        boolean novoAgendamento = agendamentoDAO.inserirAgendamento(5, 2, 1, Date.valueOf(amanha));
        if (novoAgendamento) {
            System.out.println("Agendamento marcado para " + amanha);
        } else {
            System.out.println("Erro ao agendar.");
        }*/

        List<Agendamento> agendamentos = agendamentoDAO.buscarTodosAgendamentos();
        System.out.println("Lista de agendamentos:");
        for (Agendamento agendamento : agendamentos) {
            System.out.println("ID PET: " + agendamento.getIdPet());
            System.out.println("ID USUARIO: " + agendamento.getIdUsuario());
            System.out.println("ID SERVICO: " + agendamento.getIdServico());
            System.out.println("DATA: " + agendamento.getDataAgendamento());
        }
    }
        }