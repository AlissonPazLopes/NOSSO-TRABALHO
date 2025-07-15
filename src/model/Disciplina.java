package model;

public class Disciplina {
    private String nome;
    private String codigo;

    public Disciplina(String nome, String codigo) {
        if(nome.isEmpty() || codigo.isEmpty()) return;
        this.nome = nome;
        this.codigo = codigo.toUpperCase();
    }

    public String getNome() {return nome;}
    public String getCodigo() {return codigo;}

}
