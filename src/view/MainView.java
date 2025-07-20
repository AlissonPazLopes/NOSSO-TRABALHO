package view;

import java.util.*;
import model.*;

public class MainView implements Observer {
    private Model model;
    private MainController controller;
    private int totalUsuarios;
    private boolean finalizar = false;

    //Vai sinalizar para encerrar o sistema
    public void finalizarSistema() {
        finalizar = true;
    }

    //Inicializa o sistema principal
    public void init(Model model) {
        if(model != null) {
            this.model = model;
            controller = new MainController();
            controller.init(model, this);
            model.attachObserver(this);
            model.notifica();
            menuPrincipal();
        }
    }

    //Exibe o menu principal com as opções
    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        String opcoes[] = {"1 - Login", "2 - Registrar-se", "3 - Sair"};

        do {
            System.out.println("""
            ================================================
            ||   Bem-vindo ao Sistema do Plano de Ensino  ||
            ================================================

            Escolha uma opção:
            """);
            System.out.println("Total de usuários: " + totalUsuarios + "\n");
            System.out.println(opcoes[0]);
            System.out.println(opcoes[1]);
            System.out.println(opcoes[2]);
            System.out.print("\nDigite a opção desejada: ");
            String event = sc.nextLine();
            controller.handleEvent(event);
        } while(!finalizar);
    }

    //Atualiza o numero de usuarios registrados
    public void update() {totalUsuarios = model.getTotalUsuarios();}
}
