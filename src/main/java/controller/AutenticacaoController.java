package controller;

import dao.*;
import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/login.jsp")
public class AutenticacaoController implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização do filtro
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false); // Obtém a sessão atual, mas não cria uma nova
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        if (session != null && session.getAttribute("token") != null) {
            ArrayList<Pet> pets = new PetDAO().getPets(idUsuario);
            ArrayList<Agendamento> agendamentos = new AgendamentoDAO().buscarTodosAgendamentosPorIdUsuario(idUsuario);
            ArrayList<Servico> servicos = new ServicoDAO().buscarTodosServicos();
            ArrayList<Raca> racas = new RacaDAO().getRacas();
            ArrayList<Tipo> tipos = new TipoDAO().getTipos();
            req.setAttribute("pets", pets);
            req.setAttribute("agendamentos", agendamentos);
            req.setAttribute("servicos", servicos);
            req.setAttribute("racas", racas);
            req.setAttribute("tipos", tipos);
            resp.sendRedirect(req.getContextPath() + "/home");

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        // Liberação de recursos do filtro
    }
}
