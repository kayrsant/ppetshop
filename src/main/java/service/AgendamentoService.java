package service;

import dao.AgendamentoDAO;
import model.Agendamento;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class AgendamentoService {

    public boolean cadastrar(int idUsuario, String idPet, String idServico, Timestamp dataAgendamento){

        Agendamento a;
                if(new AgendamentoDAO().inserirAgendamento(idUsuario, idPet, idServico, dataAgendamento)){
                    System.out.println("Inserido agendamento.");
                    return true;
                } else{
            System.out.println("Problema na criação");
            return false;
        }
    }
}