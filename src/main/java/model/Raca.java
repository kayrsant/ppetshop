package model;

public class Raca {
    private int id;
    private int idTipo;
    private String nome;

    public Raca(){

    }

    public Raca(int id, int idTipo, String nome) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
    }

    public Raca(int idTipo, String nome){
        this.idTipo = idTipo;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
