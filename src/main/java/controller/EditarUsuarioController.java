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

@WebServlet("editarusuario")
public class EditarUsuarioController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        System.out.println("Bateu no Editar usuario");

        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String cpf = req.getParameter("cpf");
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");
        String permissao = req.getParameter("permissao");

        int idUsuarioInt = (Integer) session.getAttribute("idUsuario");
        ArrayList<Pet> pets = new PetDAO().getPets(idUsuarioInt);
        ArrayList<Agendamento> agendamentos = new AgendamentoDAO().buscarTodosAgendamentosPorIdUsuario(idUsuarioInt);
        ArrayList<Servico> servicos = new ServicoDAO().buscarTodosServicos();
        ArrayList<Raca> racas = new RacaDAO().getRacas();
        ArrayList<Tipo> tipos = new TipoDAO().getTipos();

        RequestDispatcher dispatcher;

        if(senha == null){
            Usuario u = new UsuarioDAO().getUsuarioPorId(idUsuarioInt);
            req.setAttribute("usuario", u);
            dispatcher = req.getRequestDispatcher("/WEB-INF/editarUsuario.jsp");
            dispatcher.forward(req, resp);
        }
        if (new UsuarioDAO().atualizar(nome, cpf, email, login, senha, permissao, idUsuarioInt)){
                    req.setAttribute("pets", pets);
                    req.setAttribute("agendamentos", agendamentos);
                    req.setAttribute("servicos", servicos);
                    req.setAttribute("racas", racas);
                    req.setAttribute("tipos", tipos);
                    //dispatcher = req.getRequestDispatcher("/WEB-INF/principal.jsp");

                    resp.sendRedirect(req.getContextPath() + "/home");
                } else{
            System.out.println("Problema na atualização!");
        }
                //dispatcher.forward(req, resp);
            }
        }
