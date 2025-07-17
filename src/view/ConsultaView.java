package view;

import java.util.*;
import model.*;

public class ConsultaView implements Observer {
    private Model model;
    private ConsultaController controller;
    private String busca;
    private ArrayList<Ped> ultimaBusca;

    public void init(Model model) {
        this.model = model;
        this.controller = new ConsultaController();
        controller.init(model, this);
        model.attachObserver(this);
        menuConsulta();
    }

    public void menuConsulta() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while(continuar) {
             System.out.println("""
            =================================================
            ||              Consulta de Planos             ||
            =================================================
            """);
            System.out.println("\nDigite o nome da disciplina para buscar (ou digite 0 para voltar ao menu anterior): ");
            busca = sc.nextLine().trim().toLowerCase();

            if(busca.equals("0")) {
                controller.handleEvent("VOLTAR");
                continuar = false;
                break;
            }

            controller.handleEvent("OK");
        }
        model.detachObserver(this);
    }

    public String getBusca() {
        return busca;
    }

    public void setUltimaBusca(ArrayList<Ped> lista) {
        this.ultimaBusca = lista;
    }

    public ArrayList<Ped> getUltimaBusca() {
        return ultimaBusca;
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
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o número do plano de ensino para ver mais detalhes ou 0 para sair:");
            int escolha = sc.nextInt();
            if(escolha > 0 && escolha <= encontrados.size()) {
                controller.handleEvent("DETALHES:" + (escolha - 1));
            }
        }
    }

    public void exibirDetalhes(Ped ped) {
        System.out.println("===== Detalhes do Plano de Ensino =====");
        System.out.println("\nDisciplina: " + ped.getDisciplina().getNome());
        System.out.println("Código: " + ped.getDisciplina().getCodigo());
        System.out.println("Professor: " + ped.getProfessor().getNome());
        System.out.println("Caráter: " + ped.getCarater());
        System.out.println("Carga Horária: " + ped.getCargaHoraria());
        System.out.println("Requisitos: " + ped.getRequisitos());
        System.out.println("Justificativa: " + ped.getJustificativa());
        System.out.println("Ementa: " + ped.getEmenta());
        System.out.println("Objetivos: " + ped.getObjetivos());
        System.out.println();

        System.out.println("\n============= Calendario de Atividades =============");
        System.out.println();
        for(Atividade atividade: ped.getCalendario()) {
            System.out.println(atividade.getData() + " - " + atividade.getDescricao() + " - " + atividade.getCargaHoraria());
        }
        System.out.println();

        System.out.println("Metodologia: " + ped.getMetodologia());
        System.out.println("Atividades Discentes: " + ped.getAtividadesDiscentes());
        System.out.println("Sistema de Avaliação: " + ped.getSistemaAvaliacao());
        System.out.println("Bibliografia: " + ped.getBibliografia());    
    }
}
