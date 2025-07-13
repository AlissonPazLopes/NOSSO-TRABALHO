package view;

import java.util.*;
import model.*;

public class CriarPlanoView implements Observer{
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
        System.out.println("""
        ================================================
        ||             Criar Plano de Ensino          ||         
        ================================================
        """);
        System.out.println("Digite o nome da disciplina: ");
        disciplina = sc.nextLine();
        System.out.println("Digite o codigo da disciplina: ");
        codigo = sc.nextLine();
        
        System.out.println(tiposCarater[0]);
        System.out.println(tiposCarater[1]);
        System.out.println("Escolha o carater da disciplina: ");
        String caraterOpcao = sc.nextLine();
        if(caraterOpcao.equals("1")) {
            carater = CaraterDisciplina.OBRIGATORIA;
        } else if(caraterOpcao.equals("2")) {
            carater = CaraterDisciplina.OPTATIVA;
        }
        System.out.println();
        System.out.println(cargas[0]);
        System.out.println(cargas[1]);
        System.out.println("Escolha a carga horaria da disciplina: ");
        String cargaOpcao = sc.nextLine();
        if(cargaOpcao.equals("1")) {
            cargaHoraria = CargaHoraria.HORAS_32;
        } else if(cargaOpcao.equals("2")) {
            cargaHoraria = CargaHoraria.HORAS_64;
        }
        System.out.println();
        System.out.println("Digite os requisitos da disciplina (se houver): ");
        requisitos = sc.nextLine();
    }
}
