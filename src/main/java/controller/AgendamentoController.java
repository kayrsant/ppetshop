package controller;

import dao.*;
import model.*;
import service.AgendamentoService;
import service.PetService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("agendar")
public class AgendamentoController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String acao = req.getParameter("acao");

        String idUsuario = req.getParameter("idUsuario");
        String idPet = req.getParameter("pet");
        String idServico = req.getParameter("servico");
        String dataAgendamento = req.getParameter("dataagendamento");
        int idUsuarioInt = (Integer) session.getAttribute("idUsuario");
        ArrayList<Pet> pets = new PetDAO().getPets(idUsuarioInt);
        ArrayList<Agendamento> agendamentos = new AgendamentoDAO().buscarTodosAgendamentosPorIdUsuario(idUsuarioInt);
        ArrayList<Servico> servicos = new ServicoDAO().buscarTodosServicos();
        ArrayList<Raca> racas = new RacaDAO().getRacas();
        ArrayList<Tipo> tipos = new TipoDAO().getTipos();

        if(acao.equals("criar")){
            if (idUsuario == null || idUsuario.isEmpty()) {
                req.setAttribute("pets", pets);
                req.setAttribute("servicos", servicos);
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/agendar.jsp");
                dispatcher.forward(req, resp);
            } else {
                // Os campos não estão vazios, execute o restante da lógica
                RequestDispatcher dispatcher;

                System.out.println("Usuário: " + idUsuario);
                System.out.println("Pet: " + idPet);
                System.out.println("Servico: " + idServico);
                System.out.println("Data: " + dataAgendamento);

                String formatoEsperado = "yyyy-MM-dd'T'HH:mm";
                String formatoTimestamp = "yyyy-MM-dd HH:mm:ss";

                SimpleDateFormat sdf = new SimpleDateFormat(formatoEsperado);
                Date date = null;
                try {
                    date = sdf.parse(dataAgendamento);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                SimpleDateFormat sdfTimestamp = new SimpleDateFormat(formatoTimestamp);
                String dataFormatada = sdfTimestamp.format(date);

                Timestamp timestamp = Timestamp.valueOf(dataFormatada);

                if (new AgendamentoService().cadastrar(idUsuarioInt, idPet, idServico, timestamp)){
                    if (new PetService().hasPet(Integer.parseInt(idUsuario))) {
                        if (pets.isEmpty()) {
                            System.out.println("Tá vazio!");
                            //dispatcher = req.getRequestDispatcher("/WEB-INF/principal.jsp");
                            resp.sendRedirect(req.getContextPath() + "/home");
                        } else {
                            req.setAttribute("pets", pets);
                            req.setAttribute("agendamentos", agendamentos);
                            req.setAttribute("servicos", servicos);
                            req.setAttribute("racas", racas);
                            req.setAttribute("tipos", tipos);
                            //dispatcher = req.getRequestDispatcher("/WEB-INF/principal.jsp");

                            resp.sendRedirect(req.getContextPath() + "/home");
                        }
                        //dispatcher.forward(req, resp);
                    }
                } else{
                    req.setAttribute("pets", pets);
                    req.setAttribute("servicos", servicos);
                    dispatcher = req.getRequestDispatcher("/WEB-INF/agendar.jsp");
                    dispatcher.forward(req, resp);
                }


            }
        } else if(acao.equals("editar")){
            String idAgendamento = req.getParameter("idAgendamento");
            Agendamento agendamento = new AgendamentoDAO().buscarAgendamentoPorId(Integer.parseInt(idAgendamento));

            if (agendamento != null) {
                // Preencha os campos do formulário com os dados do agendamento
                req.setAttribute("agendamento", agendamento);
                req.setAttribute("pets", pets);
                req.setAttribute("servicos", servicos);

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/editarAgendamento.jsp");
                dispatcher.forward(req, resp);
            } else {
                // Agendamento não encontrado, redirecione ou mostre uma mensagem de erro
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else if (acao.equals("excluir")) {
            String idAgendamento = req.getParameter("idAgendamento");

            if (new AgendamentoDAO().deletarAgendamento(Integer.parseInt(idAgendamento))) {
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        }
        else{
            System.out.println("Nenhuma ação escolhida.");
        }

        }
}
