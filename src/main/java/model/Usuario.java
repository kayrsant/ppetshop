package model;

import java.util.Date;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private Date dataCadastro;
    private String login;
    private String senha;
    private int permissao;

    public Usuario() {

    }

    public Usuario(String nome, String cpf, String permissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.permissao = Integer.parseInt(permissao);
    }

    public Usuario(int id){
        this.id = id;
    }

    public Usuario(int id, String nome, String cpf, String email, Date dataCadastro, String login, String senha, int permissao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.login = login;
        this.senha = senha;
        this.permissao = permissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPermissao() {
        return permissao;
    }

    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }
}

