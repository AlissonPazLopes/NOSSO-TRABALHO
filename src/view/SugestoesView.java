package view;

import java.util.*;
import model.*;

public class SugestoesView implements Observer {
    private Model model;
    private SugestoesController controller;

    public void init(Model model) {
        this.model = model;
        this.controller = new SugestoesController();
        controller.init(model, this);
        model.attachObserver(this);
        menuSugestoes();
    }

    public void menuSugestoes() {
        Aluno aluno = (Aluno) model.getUsuarioAutenticado();
        String cursoAluno = aluno.getCurso();
        ArrayList<Ped> sugestoes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("""
            =================================================
            ||        Sugestoes de Planos de Ensino        ||
            =================================================
            """);

            
            for(Ped ped : model.getPeds()) {
                if(ped.getCurso().equalsIgnoreCase(cursoAluno)) {
                    sugestoes.add(ped);
                }
            }
            if(!sugestoes.isEmpty()) {
                System.out.println();
                System.out.println("Aqui estão as sugestões de planos de ensino baseado no seu curso:");
                System.out.println();
                int i = 1;
                for(Ped ped : sugestoes) {
                    System.out.printf("%d - Disciplina: %s | Professor: %s\n",
                        i++, ped.getDisciplina().getNome(), ped.getProfessor().getNome());
                }
                System.out.println("Escolha o número do Plano de Ensino para ver detalhes (ou digite 0 para voltar): ");
                int escolha = sc.nextInt();
                if(escolha <= 0 || escolha > sugestoes.size()) {
                    controller.handleEvent("VOLTAR");
                    return;
                }
                controller.handleEvent("DETALHES:" + (escolha - 1));
            } else {
                System.out.println("Nenhum plano de ensino cadastrado para o seu curso.");
                controller.handleEvent("VOLTAR");
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

    public void opcoesAposDetalhes() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\nO que deseja fazer?");
    System.out.println("0 - Voltar para sugestoes");
    System.out.println("1 - Voltar ao menu principal");
    String opcao = sc.nextLine();
    if (opcao.equals("0")) {
        controller.handleEvent("VOLTAR_SUGESTOES");
    } else {
        controller.handleEvent("VOLTAR");
    }
}

    public void update() {}
}
