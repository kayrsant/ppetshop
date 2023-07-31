package controller;

import dao.UsuarioDAO;
import model.Usuario;
import service.CadastrarService;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet("/cadastrar")
public class CadastrarUsuarioController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Bateu no cadastro.");

        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf");
        String email = req.getParameter("email");
        String date = String.valueOf(LocalDateTime.now());
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");
        String permissao = "1";

        System.out.println("Dados pegos: " + nome + " " + cpf + " " + email + " " + login + " " + senha + " " + date);

        RequestDispatcher dispatcher;
        if (new CadastrarService().cadastrar(nome, cpf, email, date, login, senha, permissao)) {
            try {
                String token = UUID.randomUUID().toString();
                HttpSession session = req.getSession(true);
                Usuario usuario = new UsuarioDAO().getUsuario(login);
                int idUsuario = usuario.getId();
                session.setAttribute("token", token);
                session.setAttribute("nomeUsuario", login);
                session.setAttribute("idUsuario", idUsuario);
                Thread.sleep(5000);
                //dispatcher = req.getRequestDispatcher("/WEB-INF/principal.jsp");
                resp.sendRedirect(req.getContextPath() + "/home");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("cpfInvalido", true);
            dispatcher = req.getRequestDispatcher("/cadastrar.jsp");
        }

    }
}
