package controller;

import dao.UsuarioDAO;
import model.Usuario;
import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");

        boolean loginSucessful = new LoginService().autenticar(login, senha);

        if (loginSucessful) {
            String token = UUID.randomUUID().toString();
            HttpSession session = req.getSession(true);
            Usuario usuario = new UsuarioDAO().getUsuario(login);
            int idUsuario = usuario.getId();
            session.setAttribute("token", token);
            session.setAttribute("nomeUsuario", login);
            session.setAttribute("idUsuario", idUsuario);
            req.setAttribute("loginSucessful", loginSucessful);
            //RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/principal.jsp");
            resp.sendRedirect(req.getContextPath() + "/home");
            //dispatcher.forward(req, resp);
        } else {
            req.setAttribute("loginIncorreto", true);
            try {
                Thread.sleep(1000);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
                dispatcher.forward(req, resp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
