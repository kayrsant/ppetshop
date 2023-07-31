package controller;

import dao.*;
import model.*;
import service.PetService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("editarpet")
public class EditarPetController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String idPet = req.getParameter("idPet");
        String idUsuario = req.getParameter("idUsuario");
        String idTipo = req.getParameter("tipo");
        String idRaca = req.getParameter("raca");
        String nome = req.getParameter("nome");
        String idade = req.getParameter("idade");
        int idUsuarioInt = (Integer) session.getAttribute("idUsuario");
        ArrayList<Pet> pets = new PetDAO().getPets(idUsuarioInt);
        ArrayList<Agendamento> agendamentos = new AgendamentoDAO().buscarTodosAgendamentosPorIdUsuario(idUsuarioInt);
        ArrayList<Servico> servicos = new ServicoDAO().buscarTodosServicos();
        ArrayList<Raca> racas = new RacaDAO().getRacas();
        ArrayList<Tipo> tipos = new TipoDAO().getTipos();

        RequestDispatcher dispatcher;

        if (new PetDAO().atualizar(idRaca, idTipo, nome, idade, idPet)){
            if (new PetService().hasPet(idUsuarioInt)) {
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
            Pet p = new PetDAO().getPet(idUsuarioInt, Integer.parseInt(idPet));
            req.setAttribute("pet", p);
            req.setAttribute("racas", racas);
            req.setAttribute("tipos", tipos);
            dispatcher = req.getRequestDispatcher("/WEB-INF/editarPet.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
