package view;

import java.util.*;
import model.*;

public class HomeAlunoView implements Observer {
    private Model model;
    private HomeAlunoController controller;

    //Inicializa a view com o model e o controller
    public void init(Model model) {
        this.model = model;
        this.controller = new HomeAlunoController();
        controller.init(model, this);
        model.attachObserver(this);
        menuAluno();
    }

    //Exibe o menu do aluno com as opções
    public void menuAluno() {
        Scanner sc = new Scanner(System.in);
        String opcoes[] = {
            "1 - Consultar Planos de Ensino",
            "2 - Ver Planos de Ensino Sugeridos",
            "3 - Ver Perfil",
            "4 - Logout"
        };
        System.out.println("""
        ================================================
        ||              Bem-vindo, Aluno!             ||         
        ================================================

        Escolha uma opção:
        """);
        System.out.println(opcoes[0]);
        System.out.println(opcoes[1]);
        System.out.println(opcoes[2]);
        System.out.println(opcoes[3]);
        System.out.print("\nDigite a opção desejada: ");
        String event = sc.nextLine();
        controller.handleEvent(event);
    }

    //atualiza o observer
    public void update() {

    }

    public void exibirMsg(String msg) {
        System.out.println();
        System.out.println(msg);
        System.out.println();
    }
    
}
