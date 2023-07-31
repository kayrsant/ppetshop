package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class DeslogarController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("Sessão " + session.getAttribute("nomeUsuario") + " invalidada.");
            session.invalidate(); // Invalida a sessão do usuário
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}
