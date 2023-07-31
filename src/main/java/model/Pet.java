package model;

public class Pet {

    private int id;
    private int idRaca;
    private int idTipo;
    private int idUsuario;
    private String nome;
    private int idade;

    public Pet(int id){
        this.id = id;
    }

    public Pet(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    public Pet(int idRaca, int idTipo, int idUsuario, String nome, int idade){
        this.idRaca = idRaca;
        this.idTipo = idTipo;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.idade = idade;
    }

    public Pet(int id, int idRaca, int idTipo, int idUsuario, String nome, int idade) {
        this.id = id;
        this.idRaca = idRaca;
        this.idTipo = idTipo;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(int idRaca) {
        this.idRaca = idRaca;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
