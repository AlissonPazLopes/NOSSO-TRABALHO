package view;

import java.util.*;
import model.*;

public class CriarPlanoView implements Observer {
    private Model model;
    private CriarPlanoController controller;
    private String disciplina;
    private String codigo;
    private CaraterDisciplina carater;
    private CargaHoraria cargaHoraria;
    private String requisitos;
    private String justificativa;
    private String ementa;
    private String objetivos;
    private String metodologia;
    private String atividadesDiscentes;
    private String sistemaAvaliacao;
    private String bibliografia;

    public void init(Model model) {
        this.model = model;
        this.controller = new CriarPlanoController();
        controller.init(model, this);
        model.attachObserver(this);
        menuCriarPlano();
    }

    public void menuCriarPlano() {
        Scanner sc = new Scanner(System.in);
        String tiposCarater[] = {"1 - Obrigatoria", "2 - Optativa"};
        String cargas[] = {"1 - 32 horas", "2 - 64 horas"};
        String resposta;
        do {
            System.out.println("""
            =================================================
            ||             Criar Plano de Ensino          ||         
            =================================================
            """);
            System.out.println("Digite o nome da disciplina: ");
            disciplina = sc.nextLine();
            System.out.println("Digite o codigo da disciplina: ");
            codigo = sc.nextLine();

            System.out.println(tiposCarater[0]);
            System.out.println(tiposCarater[1]);
            System.out.println("Escolha o carater da disciplina: ");
            String caraterOpcao = sc.nextLine();
            if (caraterOpcao.equals("1")) {
            carater = CaraterDisciplina.OBRIGATORIA;
            } else if (caraterOpcao.equals("2")) {
            carater = CaraterDisciplina.OPTATIVA;
            }
            System.out.println();
            System.out.println(cargas[0]);
            System.out.println(cargas[1]);
            System.out.println("Escolha a carga horaria da disciplina: ");
            String cargaOpcao = sc.nextLine();
            if (cargaOpcao.equals("1")) {
            cargaHoraria = CargaHoraria.HORAS_32;
            } else if (cargaOpcao.equals("2")) {
            cargaHoraria = CargaHoraria.HORAS_64;
            }
            System.out.println();
            System.out.println("Digite os requisitos da disciplina (se houver): ");
            requisitos = sc.nextLine();
            System.out.println("Digite a justificativa da disciplina: ");
            justificativa = sc.nextLine();
            System.out.println("Digite a ementa da disciplina: ");
            ementa = sc.nextLine();
            System.out.println("Digite os objetivos da disciplina: ");
            objetivos = sc.nextLine();
            System.out.println("Digite a metodologia da disciplina: ");
            metodologia = sc.nextLine();
            System.out.println("Digite as atividades discentes: ");
            atividadesDiscentes = sc.nextLine();
            System.out.println("Informe o sistema de avaliacao da disciplina: ");
            sistemaAvaliacao = sc.nextLine();
            System.out.println("Informe a bibliografia da disciplina: ");
            bibliografia = sc.nextLine();
            System.out.println(String.format("""
            Nome: %s
            Codigo: %s
            Carater: %s
            Carga horaria: %s
            Requisitos: %s
            Justificativa: %s
            Ementa: %s
            Objetivos: %s
            Metodologia: %s
            Atividades discentes: %s
            Sistema de avaliacao: %s
            Bibliografia: %s
            """,
            disciplina,
            codigo,
            carater,
            cargaHoraria,
            requisitos,
            justificativa,
            ementa,
            objetivos,
            metodologia,
            atividadesDiscentes,
            sistemaAvaliacao,
            bibliografia));
            System.out.println("Os dados estao corretos? (S/N)");
            resposta = sc.nextLine().trim().toUpperCase();
        } while (resposta.equals("N"));
        controller.handleEvent("OK");
        model.detachObserver(this);
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getCodigo() {
        return codigo;
    }

    public CaraterDisciplina getCarater() {
        return carater;
    }

    public CargaHoraria getCargaHoraria() {
        return cargaHoraria;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public String getEmenta() {
        return ementa;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public String getAtividadesDiscentes() {
        return atividadesDiscentes;
    }

    public String getSistemaAvaliacao() {
        return sistemaAvaliacao;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void update() {

    }

    public void exibirMsg(String msg) {
        System.out.println();
        System.out.println(msg);
        System.out.println();
    }
}
