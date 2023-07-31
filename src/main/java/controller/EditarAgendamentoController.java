package controller;

import dao.*;
import model.*;
import service.AgendamentoService;
import service.PetService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("editaragendamento")
public class EditarAgendamentoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String idAgendamento = req.getParameter("idAgendamento");
        String idUsuario = req.getParameter("idUsuario");
        String pet = req.getParameter("pet");
        String servico = req.getParameter("servico");
        String dataAgendamento = req.getParameter("dataagendamento");
        int idUsuarioInt = (Integer) session.getAttribute("idUsuario");
        ArrayList<Pet> pets = new PetDAO().getPets(idUsuarioInt);
        ArrayList<Agendamento> agendamentos = new AgendamentoDAO().buscarTodosAgendamentosPorIdUsuario(idUsuarioInt);
        ArrayList<Servico> servicos = new ServicoDAO().buscarTodosServicos();
        ArrayList<Raca> racas = new RacaDAO().getRacas();
        ArrayList<Tipo> tipos = new TipoDAO().getTipos();

        RequestDispatcher dispatcher;

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

        if (new AgendamentoDAO().atualizarAgendamento(idUsuarioInt, pet, servico, timestamp, idAgendamento)){
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
            req.setAttribute("pets", pets);
            req.setAttribute("servicos", servicos);
            dispatcher = req.getRequestDispatcher("/WEB-INF/agendar.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
