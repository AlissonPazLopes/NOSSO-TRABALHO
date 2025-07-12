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
}


/*  // possível maneira de fazer classe professor, decidir qual é a melhor
package model;

import java.util.ArrayList;

public class Professor extends Usuario {
    private ArrayList<String> disciplinasLecionadas = new ArrayList<>(); // Usar uma classe Disciplina talvez?? (toda disciplina tem um nome, codigo, carga horaria, etc)
    private ArrayList<Ped> pedsCriados = new ArrayList<>();

    public Professor(String nome, String login, String senha) {
        super(nome, login, senha);
    }
}
 */