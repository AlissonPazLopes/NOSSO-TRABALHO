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
        ArrayList<Atividade> calendario = new ArrayList<>();
        calendario.add(new Atividade("20/10", "aula 1", 8));
        calendario.add(new Atividade("22/10", "aula 2", 8));
        calendario.add(new Atividade("24/10", "aula 3", 8));
        calendario.add(new Atividade("26/10", "aula 4", 8));

        Model.getInstancia().cadastrarPed(
                "RUS123",
                felipe,
                "Ciência da Computação",
                "Algebra Linear",
                CaraterDisciplina.OBRIGATORIA,
                CargaHoraria.HORAS_32,
                "requisitos",
                "justificativa",
                "ementa",
                "objetivos",
                calendario,
                "metodologia",
                "atividadesDiscentes",
                "sistemaAvaliacao",
                "bibliografia"
                );



        ArrayList<Atividade> calendario = new ArrayList<>();
        calendario.add(new Atividade("20/10", "aula 1", 8));
        calendario.add(new Atividade("22/10", "aula 2", 8));
        calendario.add(new Atividade("24/10", "aula 3", 8));
        calendario.add(new Atividade("26/10", "aula 4", 8));

        Model.getInstancia().cadastrarPed(
                "RUS123",
                felipe,
                "Ciência da Computação",
                "Algebra Linear",
                CaraterDisciplina.OBRIGATORIA,
                CargaHoraria.HORAS_32,
                "requisitos",
                "justificativa",
                "ementa",
                "objetivos",
                calendario,
                "metodologia",
                "atividadesDiscentes",
                "sistemaAvaliacao",
                "bibliografia"
        );
        
        Model model = Model.getInstancia();
        MainView view = new MainView();
        view.init(model);

    }
}
