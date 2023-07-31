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

@WebServlet("pet")
public class PetController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Bateu no pet.");
        HttpSession session = req.getSession();

        String acao = req.getParameter("acao");

        int idUsuarioInt = (Integer) session.getAttribute("idUsuario");

        ArrayList<Raca> racas = new RacaDAO().getRacas();
        ArrayList<Tipo> tipos = new TipoDAO().getTipos();
        ArrayList<Pet> pets = new PetDAO().getPets(idUsuarioInt);
        ArrayList<Servico> servicos = new ServicoDAO().buscarTodosServicos();

        String idRaca = req.getParameter("raca");
        String idTipo = req.getParameter("tipo");
        String idUsuario = req.getParameter("idUsuario");
        String nome = req.getParameter("nome");
        String idade = req.getParameter("idade");

        if(acao.equals("criar")){
            if(idRaca == null || idRaca.isEmpty()) {
                req.setAttribute("racas", racas);
                req.setAttribute("tipos", tipos);
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/cadastrarPet.jsp");
                dispatcher.forward(req, resp);

            } else {
                if(new PetService().cadastrar(idRaca, idTipo, idUsuario, nome, idade)){

                    System.out.println("ID Usuário: " + idUsuario);
                    System.out.println("Raça: " + idRaca);
                    System.out.println("Tipo: " + idTipo);
                    System.out.println("Nome: " + nome);
                    System.out.println("Idade: " + idade);

                    req.setAttribute("racas", racas);
                    req.setAttribute("tipos", tipos);
                    req.setAttribute("pets", pets);
                    req.setAttribute("servicos", servicos);
                    resp.sendRedirect(req.getContextPath() + "/home");
                } else{
                    req.setAttribute("racas", racas);
                    req.setAttribute("tipos", tipos);
                    resp.sendRedirect(req.getContextPath() + "/pet");
                }
            }
        } else if (acao.equals("editar")) {
            String idPet = req.getParameter("idPet");
            idRaca = req.getParameter("idRaca");
            idTipo = req.getParameter("idTipo");

            if (idPet != null && !idPet.isEmpty()) {
                Pet p = new PetDAO().getPet(idUsuarioInt, Integer.parseInt(idPet));

                if (p != null) {
                    req.setAttribute("pet", p);
                    req.setAttribute("servicos", servicos);
                    req.setAttribute("racas", racas);
                    req.setAttribute("tipos", tipos);

                    System.out.println("Entrei no if do editar!");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/editarPet.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    // Agendamento não encontrado, redirecione ou mostre uma mensagem de erro
                    System.out.println("Agendamento não encontrado, redirecione ou mostre uma mensagem de erro");
                    resp.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                // idPet está ausente ou vazio, redirecione ou mostre uma mensagem de erro
                System.out.println("idPet está ausente ou vazio, redirecione ou mostre uma mensagem de erro");
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        }
        else if (acao.equals("excluir")) {
            String idPet = req.getParameter("idPet");
            System.out.println("ID DO PET EXCLUIR: " + idPet);

            if (new PetDAO().deletar(Integer.parseInt(idPet))) {
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
