package view;

import java.util.*;
import model.*;

public class PlanosCriadosView implements Observer {
    private Model model;
    private PlanosCriadosController controller;

    public void init(Model model) {
        this.model = model;
        this.controller = new PlanosCriadosController();
        controller.init(model, this);
        model.attachObserver(this);
        menuPlanosCriados();
    }

    public void menuPlanosCriados() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        ================================================
        ||          Planos de Ensino Criados          ||
        ================================================
        """);

        Professor professor = (Professor) model.getUsuarioAutenticado();
        ArrayList<Ped> pedsProfessor = new ArrayList<>();
        for(Ped ped : model.getPeds()) {
            if(ped.getProfessor().equals(professor)) {
                pedsProfessor.add(ped);
            }
        }
        if(pedsProfessor.isEmpty()) {
            System.out.println("Você não possui Planos de Ensino criados.\n");
            controller.handleEvent("VOLTAR");
            return;
        } else {
            int i = 1;
            for(Ped ped : pedsProfessor) {
                System.out.printf("%d - Disciplina: %s | Curso: %s | Código: %s\n",
                    i++, ped.getDisciplina().getNome(), ped.getCurso(), ped.getDisciplina().getCodigo());
            }
        }
        System.out.println("Escolha o número do Plano de Ensino para ver detalhes (ou digite 0 para voltar): ");
        int escolha = sc.nextInt();
        if(escolha <= 0 || escolha > pedsProfessor.size()) {
            controller.handleEvent("VOLTAR");
            return;
        }
        controller.handleEvent("DETALHES:" + (escolha - 1));
    }

    public void update() {

    }

    public void opcoesPlano(Ped ped) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("1 - Editar o Plano de ensino");
            System.out.println("2 - Remover o Plano de ensino");
            System.out.println("0 - Voltar");
            String opcao = sc.nextLine();
            switch (opcao) {
                case "1" -> {
                    controller.handleEvent("EDITAR:" + ped.getDisciplina().getCodigo());
                    return;
                }
                case "2" -> {
                    controller.handleEvent("REMOVER:" + ped.getDisciplina().getCodigo());
                    return;
                }
                case "0" -> {
                    controller.handleEvent("VOLTAR");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void exibirDetalhes(Ped ped) {
        System.out.println("===== Detalhes do Plano de Ensino =====");
        System.out.println("\nDisciplina: " + ped.getDisciplina().getNome());
        System.out.println("Código: " + ped.getDisciplina().getCodigo());
        System.out.println("Curso: " + ped.getCurso());
        System.out.println("Professor: " + ped.getProfessor().getNome());
        System.out.println("Caráter: " + ped.getCarater());
        System.out.println("Carga Horária: " + ped.getCargaHoraria().getHoras() + " horas");
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

    public boolean confirmarRemocao(Ped ped, Professor professor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite sua senha para confirmar a exclusão do Plano de Ensino: ");
        String senha = sc.nextLine();

        if(!professor.getSenha().equals(senha)) {
            System.out.println("Senha incorreta. Operação cancelada.");
            return false;
        }
        return true;
    }

    public void exibirMsg(String msg) {
        System.out.println();
        System.out.println(msg);
        System.out.println();
    }
}
