package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Agendamento {

    private int id;
    private int idUsuario;
    private int idPet;
    private int idServico;
    private Timestamp dataAgendamento;

    public Agendamento(){

    }
    public Agendamento (int idUsuario, int idPet, int idServico, Timestamp dataAgendamento){
        this.idUsuario = idUsuario;
        this.idPet = idPet;
        this.idServico = idServico;
        this.dataAgendamento = dataAgendamento;
    }

    public Agendamento(int id, int idUsuario, int idPet, int idServico, Timestamp dataAgendamento) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPet = idPet;
        this.idServico = idServico;
        this.dataAgendamento = dataAgendamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public Timestamp getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Timestamp dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
