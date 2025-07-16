import model.*;
import view.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //adicionando um usuario no model
        Disciplina algebra = new Disciplina("Algebra Linear", "RUS123");
        Disciplina grafos = new Disciplina("Algoritmos em Grafos", "RUS234");
        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
        disciplinas.add(algebra);
        disciplinas.add(grafos);
        Professor felipe = new Professor("Felipe", "felipe", "12345678", disciplinas);
        Model.getInstancia().setUsuario(felipe);

        Model model = Model.getInstancia();
        MainView view = new MainView();
        view.init(model);    
    }
}
