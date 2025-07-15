package view;

import java.util.*;
import model.*;

public class ConsultaView implements Observer {
    private Model model;
    private ConsultaController controller;
    private String busca;

    public void init(Model model) {
        this.model = model;
        this.controller = new ConsultaController();
        controller.init(model, this);
        model.attachObserver(this);
        menuConsulta();
    }

    public void menuConsulta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        ================================================
        ||              Consulta de Planos             ||
        ================================================
        """);
        System.out.println("Digite o nome da disciplina para buscar");
        busca = sc.nextLine().trim().toLowerCase();

        controller.handleEvent("OK");
        model.detachObserver(this);
    }

    public String getBusca() {
        return busca;
    }

    public void update() {
        
    }

    public void exibirResultados(ArrayList<Ped> encontrados) {
        if(encontrados.isEmpty()) {
            System.out.println("Nenhum plano de ensino encontrado para essa busca.");
        } else {
            System.out.println("Planos de ensino encontrados:");
            int i = 1;
            for(Ped ped : encontrados) {
                System.out.printf("%d - Disciplina: %s | Professor: %s\n", 
                    i++, ped.getDisciplina().getNome(), ped.getProfessor().getNome());
            }
        }
    }
}
