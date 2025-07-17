package model;

import java.util.HashMap;
import java.util.ArrayList;

public class Professor extends Usuario {
    private HashMap<String, Disciplina> disciplinas = new HashMap<>();
    private int chaveAddDisciplina; //usado no perfilController

    public Professor(String nome, String login, String senha, ArrayList<Disciplina> disciplinas) {
        super(nome, login, senha);
        for(Disciplina d : disciplinas) {setDisciplina(d);}
    }

    public void setDisciplina(Disciplina disciplina) {disciplinas.put(disciplina.getCodigo(), disciplina);}
    public void removeDisciplina(Disciplina disciplina) {disciplinas.remove(disciplina.getCodigo());}

    public Disciplina getDisciplina(String codigo) {return disciplinas.get(codigo);}
    public HashMap<String, Disciplina> getDisciplinas() {return disciplinas;}

    public int getTotalDisciplinas() {return disciplinas.size();}

    public int getChaveAddDisciplina() {return chaveAddDisciplina;}

    public String toString() {

        String perfil = "[1] - Nome: " + getNome() +
                        "\n[2] - Login: " + getLogin() +
                        "\n[3] - Senha: " + getSenha();

        int i = 4;
        for (Disciplina disciplina : disciplinas.values()) {
            perfil += "\n[" + i + "] - Disciplina: " + disciplina.getNome(); // Pega o nome da disciplina
            i++;
        }

        chaveAddDisciplina = i;

        return perfil;
    }
}
