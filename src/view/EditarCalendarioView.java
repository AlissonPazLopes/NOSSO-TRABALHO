package view;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class EditarCalendarioView implements Observer{
    private Model model;
    private EditarCalendarioController controller;
    private Ped ped;

    public void init(Model model, Ped ped) {
        this.model = model;
        this.ped = ped;
        this.controller = new EditarCalendarioController();
        controller.init(model, this, ped);
        model.attachObserver(this);
        editarCalendario();
    }

    public void editarCalendario() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Atividade> calendario = ped.getCalendario();

        System.out.println("""
            =================================================
            ||             Editor de Calendario            ||
            =================================================
            """);

        System.out.println();
        System.out.println("0 - Sair");
        int i = 1;
        for(Atividade atividade : calendario) {
            System.out.println(i + " - " + atividade.getData() + " - " + atividade.getDescricao() + " - " + atividade.getCargaHoraria());
            i++;
        }

        System.out.println(i + " - Adicionar atividade");
        System.out.println("\nEscolha sair[0], remover uma atividade ou adicionar[" + i + "]");
        int opcao = sc.nextInt();
        sc.nextLine();
        controller.handleEvent(opcao, i);
    }

    public void exibirMsg(String msg) {
        System.out.println();
        System.out.println(msg);
        System.out.println();
    }

    public String scannerString(){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    public int scannerInt(){
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }

    public void encerrar() {model.detachObserver(this);}

    public void update() {}
}
