package model;

import model.exception.SenhaInvalidaException;

public abstract class Usuario {
    private String nome;
    private String login;
    private String senha;

    public Usuario(String nome, String login, String senha) {
        super();
        setNome(nome);
        setLogin(login);
        setSenha(senha);
    }

    public void setNome(String nome) {
        if(nome != null) {
            this.nome = nome.toUpperCase();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setLogin(String login) {
        if(login != null) {
            this.login = login;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setSenha(String senha) {
        if(senha == null || senha.length() < 8) {
            throw new SenhaInvalidaException("A senha deve conter no minimo 8 caracteres.");
        }
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
}
