package model;

import java.util.HashMap;
import java.util.ArrayList;

public class Professor extends Usuario {
    private HashMap<String, Disciplina> disciplinas = new HashMap<>();

    public Professor(String nome, String login, String senha, ArrayList<Disciplina> disciplinas) {
        super(nome, login, senha);
        for(Disciplina d : disciplinas) {setDisciplina(d);}
    }

    public void setDisciplina(Disciplina disciplina) {disciplinas.put(disciplina.getCodigo(), disciplina);}

    public Disciplina getDisciplina(String codigo) {return disciplinas.get(codigo);}

    public int getTotalDisciplinas() {return disciplinas.size();}

    public String toString() {

        String perfil = "[1] - Nome: " + getNome() +
                        "\n[2] - Login: " + getLogin() +
                        "\n[3] - Senha: " + getSenha();

        int i = 4;
        for (Disciplina disciplina : disciplinas.values()) {
            perfil += "\n[" + i + "] - Disciplina: " + disciplina.getNome(); // Pega o nome da disciplina
            i++;
        }

        return perfil;
    }
}
