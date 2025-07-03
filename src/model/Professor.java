package model;

import java.util.ArrayList;

public class Professor extends Usuario {
    private ArrayList<String> disciplinasLecionadas = new ArrayList<>(); // Usar uma classe Disciplina talvez?? (toda disciplina tem um nome, codigo, carga horaria, etc)
    private ArrayList<Ped> pedsCriados = new ArrayList<>();
    
    public Professor(String nome, String login, String senha) {
        super(nome, login, senha);
    }
}
