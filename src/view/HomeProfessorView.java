package view;

import java.util.*;
import model.*;

public class HomeProfessorView implements Observer {
    private Model model;
    private HomeProfessorController controller;

    //inicializa a view com o model e o controller
    public void init(Model model) {
        this.model = model;
        this.controller = new HomeProfessorController();
        controller.init(model, this);
        model.attachObserver(this);
        menuProfessor();
    }

    //Vai exibir o menu do professor com as opções disponiveis do mesmo
    public void menuProfessor() {
        Scanner sc = new Scanner(System.in);
        String opcoes[] = {
            "1 - Consultar Planos de Ensino",
            "2 - Criar Plano de Ensino",
            "3 - Ver Planos de Ensino Criados",
            "4 - Ver Perfil",
            "5 - Logout"
        };
        System.out.println("""
        ================================================
        ||             Bem-vindo, Professor!          ||         
        ================================================

        Escolha uma opção:
        """);
        System.out.println(opcoes[0]);
        System.out.println(opcoes[1]);
        System.out.println(opcoes[2]);
        System.out.println(opcoes[3]);
        System.out.println(opcoes[4]);
        System.out.print("\nDigite a opção desejada: ");
        String event = sc.nextLine();
        controller.handleEvent(event);
    }

    //Vai dar update quando tiver mudanças no model
    public void update() {
        
    }

    public void exibirMsg(String msg) {
        System.out.println();
        System.out.println(msg);
        System.out.println();
    }
    
}
