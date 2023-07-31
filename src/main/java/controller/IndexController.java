package controller;

import dao.*;
import model.*;
import service.PetService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/home")
public class IndexController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Index meus manos!");

        HttpSession session = req.getSession();
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        RequestDispatcher dispatcher;

            ArrayList<Pet> pets = new PetDAO().getPets(idUsuario);
            ArrayList<Agendamento> agendamentos = new AgendamentoDAO().buscarTodosAgendamentosPorIdUsuario(idUsuario);
            ArrayList<Servico> servicos = new ServicoDAO().buscarTodosServicos();
            ArrayList<Raca> racas = new RacaDAO().getRacas();
            ArrayList<Tipo> tipos = new TipoDAO().getTipos();

                if (pets.isEmpty()) {
                    System.out.println("Tá vazio!");
                    dispatcher = req.getRequestDispatcher("/WEB-INF/principal.jsp");
                } else {
                    req.setAttribute("pets", pets);
                    req.setAttribute("agendamentos", agendamentos);
                    req.setAttribute("servicos", servicos);
                    req.setAttribute("racas", racas);
                    req.setAttribute("tipos", tipos);
                    dispatcher = req.getRequestDispatcher("/WEB-INF/principal.jsp");
                }
                dispatcher.forward(req, resp);
            }
        }
