package view;


import java.util.Scanner;
import model.CaraterDisciplina;
import model.CargaHoraria;
import model.*;
import model.Ped;

public class EditarPlanoView implements Observer {
    private Model model;
    private EditarPlanoController controller;
    private Ped ped;
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

    public void init(Model model, Ped ped) {
        this.model = model;
        this.ped = ped;
        this.controller = new EditarPlanoController();
        controller.init(model, this, ped);
        model.attachObserver(this);

        // Inicializar campos com valores atuais
        carater = ped.getCarater();
        cargaHoraria = ped.getCargaHoraria();
        requisitos = ped.getRequisitos();
        justificativa = ped.getJustificativa();
        ementa = ped.getEmenta();
        objetivos = ped.getObjetivos();
        metodologia = ped.getMetodologia();
        atividadesDiscentes = ped.getAtividadesDiscentes();
        sistemaAvaliacao = ped.getSistemaAvaliacao();
        bibliografia = ped.getBibliografia();

        menuEditarPlano();
    }

    public void menuEditarPlano() {
        Scanner sc = new Scanner(System.in);
        String resposta;

        CaraterDisciplina caraterOriginal = carater;
        CargaHoraria cargaHorariaOriginal = cargaHoraria;
        String requisitosOriginal = requisitos;
        String justificativaOriginal = justificativa;
        String ementaOriginal = ementa;
        String objetivosOriginal = objetivos;
        String metodologiaOriginal = metodologia;
        String atividadesDiscentesOriginal = atividadesDiscentes;
        String sistemaAvaliacaoOriginal = sistemaAvaliacao;
        String bibliografiaOriginal = bibliografia;

        String[] tiposCarater = {"1 - Obrigatória", "2 - Optativa"};
        String[] cargas = {" 1 - 32 horas", " 2 - 64 horas", " 3 - 96 horas", " 4 - 160 horas"};

        do {
            System.out.println("""
            =================================================
            ||             Editar Plano de Ensino          ||         
            =================================================
            """);

            System.out.println("Disciplina: " + ped.getDisciplina().getNome());
            System.out.println("Código: " + ped.getDisciplina().getCodigo());
            System.out.println("Professor: " + ped.getProfessor().getNome());

            System.out.println("\nCampos editáveis:");
            boolean continuar = true;
            System.out.println(tiposCarater[0]);
            System.out.println(tiposCarater[1]);

            do {
                System.out.println("Caráter atual: " + carater);
                System.out.print("Novo caráter (ENTER para manter): ");
                String caraterOpcao = sc.nextLine();
                if(caraterOpcao == null || caraterOpcao == ""){
                    System.out.println("O Caráter Permanceu o Mesmo!: " + carater);
                    continuar = false;
                } else if(caraterOpcao.equals("1")){
                    carater = CaraterDisciplina.OBRIGATORIA;
                    System.out.println("O Caráter Atual é: OBRIGATORIA");
                    continuar = false;
                } else if (caraterOpcao.equals("2")) {
                    carater = CaraterDisciplina.OPTATIVA;
                    System.out.println("O Caráter Atual é: OPTATIVA");
                    continuar = false;
                }else {
                    System.out.println("Opção Inválida, Tente Novamente.");
                    continue;
                }

            }while(continuar);



            System.out.println(cargas[0]);
            System.out.println(cargas[1]);
            System.out.println(cargas[2]);
            System.out.println(cargas[3]);


            do {
                System.out.println("Carga horária atual: " + cargaHoraria.getHoras() + " horas");
                System.out.print("Nova carga horária (ENTER para manter): ");
                String cargaOpcao = sc.nextLine();
                if(cargaOpcao == null || cargaOpcao == ""){
                    System.out.println("Mesma carga horaria de: " + cargaHoraria.getHoras()  + " horas");
                    continuar = false;
                } else if (cargaOpcao.equals("1")){
                    cargaHoraria = CargaHoraria.HORAS_32;
                    System.out.println("Carga horária atual de: 32 horas" );
                    continuar = false;
                } else if (cargaOpcao.equals("2")) {
                    cargaHoraria = CargaHoraria.HORAS_64;
                    System.out.println("Carga horária atual de: 64 horas" );
                    continuar = false;
                } else if (cargaOpcao.equals("3")) {
                    cargaHoraria = CargaHoraria.HORAS_96;
                    System.out.println("Carga horária atual de: 96 horas" );
                    continuar = false;
                } else if (cargaOpcao.equals("4")) {
                    cargaHoraria = CargaHoraria.HORAS_160;
                    System.out.println("Carga horária atual de: 160 horas" );
                    continuar = false;
                } else {
                    System.out.println("Opção Inválida, Tente Novamente.");
                    continue;
                }
            }while(continuar);
            System.out.print("Requisitos [" + requisitos + "]: ");
            String novoRequisitos = sc.nextLine();
            if(!novoRequisitos.isEmpty()) requisitos = novoRequisitos;

            System.out.print("Justificativa [" + justificativa + "]: ");
            String novaJustificativa = sc.nextLine();
            if(!novaJustificativa.isEmpty()) justificativa = novaJustificativa;

            System.out.print("Ementa [" + ementa + "]: ");
            String novaEmenta = sc.nextLine();
            if(!novaEmenta.isEmpty()) ementa = novaEmenta;

            System.out.print("Objetivos [" + objetivos + "]: ");
            String novosObjetivos = sc.nextLine();
            if(!novosObjetivos.isEmpty()) objetivos = novosObjetivos;

            System.out.print("Editar calendário[S/N]: ");
            String novoCalendario = sc.nextLine().toUpperCase();

            //corrigir isso
            if(novoCalendario.equals("S")){
                controller.handleEvent("EDITAR_CALENDARIO");
            }

            System.out.print("Metodologia [" + metodologia + "]: ");
            String novaMetodologia = sc.nextLine();
            if(!novaMetodologia.isEmpty()) metodologia = novaMetodologia;

            System.out.print("Atividades discentes [" + atividadesDiscentes + "]: ");
            String novasAtividades = sc.nextLine();
            if(!novasAtividades.isEmpty()) atividadesDiscentes = novasAtividades;

            System.out.print("Sistema de avaliação [" + sistemaAvaliacao + "]: ");
            String novoAvaliacao = sc.nextLine();
            if(!novoAvaliacao.isEmpty()) sistemaAvaliacao = novoAvaliacao;

            System.out.print("Bibliografia [" + bibliografia + "]: ");
            String novaBibliografia = sc.nextLine();
            if(!novaBibliografia.isEmpty()) bibliografia = novaBibliografia;

            System.out.println("\nResumo das alterações feitas:");
            System.out.println("Caráter: " + carater);
            System.out.println("Carga Horária: " + cargaHoraria.getHoras() + " horas");
            System.out.println("Requisitos: " + requisitos);
            System.out.println("Justificativa: " + justificativa);
            System.out.println("Ementa: " + ementa);
            System.out.println("Objetivos: " + objetivos);
            System.out.println("\n====== Calendário de Atividades ======");
            System.out.println();
            for(Atividade atividade: ped.getCalendario()){
                System.out.println(atividade.getData() + " - " + atividade.getDescricao() + " - " + atividade.getCargaHoraria());
            }
            System.out.println();
            System.out.println("Metodologia: " + metodologia);
            System.out.println("Atividades Discentes: " + atividadesDiscentes);
            System.out.println("Sistema de Avaliação: " + sistemaAvaliacao);
            System.out.println("Bibliografia: " + bibliografia);

            System.out.print("\nConfirmar alterações? (S/N): ");
            resposta = sc.nextLine().trim().toUpperCase();

            // Restaura os valores originais
            if(resposta.equals("N")) {
                carater = caraterOriginal;
                cargaHoraria = cargaHorariaOriginal;
                requisitos = requisitosOriginal;
                justificativa = justificativaOriginal;
                ementa = ementaOriginal;
                objetivos = objetivosOriginal;
                metodologia = metodologiaOriginal;
                atividadesDiscentes = atividadesDiscentesOriginal;
                sistemaAvaliacao = sistemaAvaliacaoOriginal;
                bibliografia = bibliografiaOriginal;
                controller.handleEvent("CANCELAR");
                break;
            }   
        } while(resposta.equals("N"));

        if(resposta.equals("S")) {
            controller.handleEvent("SALVAR");
        }
        model.detachObserver(this);
    }

    // Getters para o controller
    public CaraterDisciplina getCarater() { return carater; }
    public CargaHoraria getCargaHoraria() { return cargaHoraria; }
    public String getRequisitos() { return requisitos; }
    public String getJustificativa() { return justificativa; }
    public String getEmenta() { return ementa; }
    public String getObjetivos() { return objetivos; }
    public String getMetodologia() { return metodologia; }
    public String getAtividadesDiscentes() { return atividadesDiscentes; }
    public String getSistemaAvaliacao() { return sistemaAvaliacao; }
    public String getBibliografia() { return bibliografia; }

    public void exibirMsg(String msg) {
        System.out.println("\n" + msg + "\n");
    }

    public void update() {}
}