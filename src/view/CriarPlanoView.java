package view;

import java.util.*;
import model.*;

public class CriarPlanoView implements Observer {
    private Model model;
    private CriarPlanoController controller;
    private String curso;
    private String disciplina;
    private String codigo;
    private CaraterDisciplina carater;
    private CargaHoraria cargaHoraria;
    private String requisitos;
    private String justificativa;
    private String ementa;
    private String objetivos;
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayList<String> descricoes = new ArrayList<>();
    private ArrayList<Integer> horas = new ArrayList<>();
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
        Professor professor = (Professor) model.getUsuarioAutenticado();
        ArrayList<Disciplina> disciplinasProf = new ArrayList<>(professor.getDisciplinas().values());
        Scanner sc = new Scanner(System.in);

        int horasCriadas = 0;

        String[] cursos = {
                "CC - Ciencia da computacao",
                "ES - Engenharia de Software",
                "EC - Engenharia Civil",
                "EM - Engenharia Mecânica",
                "EP - Engenharia de Produção"
            };
        String tiposCarater[] = {"1 - Obrigatoria", "2 - Optativa"};
        String cargas[] = {"1 - 32 horas", "2 - 64 horas", "3 - 96 horas", "4 - 160 horas"};
        String resposta;
        do {
            System.out.println();
            System.out.println("""
            =================================================
            ||             Criar Plano de Ensino          ||         
            =================================================
            """);

            boolean continuar = true;
            do {

                System.out.println("Cursos:\n");
                for(String c : cursos) {
                    System.out.println(c);
                }

                System.out.print("\nInforme a sigla do curso para o qual este plano é destinado: ");
                curso = sc.nextLine().toUpperCase();
                if(!curso.equals("CC") && !curso.equals("ES") && !curso.equals("EC") && !curso.equals("EM") && !curso.equals("EP")) {
                    System.out.println("\nDigite um curso válido.\n");
                    continue;
                }
                continuar = false;
            } while(continuar);

            System.out.println("\nDisciplinas ministradas:\n");
            for(int i = 0; i < disciplinasProf.size(); i++) {
                Disciplina d = disciplinasProf.get(i);
                System.out.printf("%d - %s (Código: %s)\n", i+1, d.getNome(), d.getCodigo());
            }
            int escolha = -1;
            do {
                System.out.print("\nEscolha o numero da disciplina para o qual deseja criar o plano: ");
                try {
                    escolha = Integer.parseInt(sc.nextLine()); // sem usar nextInt() para evitar problemas com o buffer
                } catch(NumberFormatException e) {
                    escolha = -1; // Força a repetição do loop se a entrada não for um número
                }
            } while(escolha < 1 || escolha > disciplinasProf.size());

            Disciplina disciplinaEscolhida = disciplinasProf.get(escolha - 1);

            disciplina = disciplinaEscolhida.getNome();
            codigo = disciplinaEscolhida.getCodigo();

            System.out.println("\nCarater da disciplina: ");
            System.out.println(tiposCarater[0]);
            System.out.println(tiposCarater[1]);
            System.out.println();
            System.out.print("Escolha o carater da disciplina: ");
            String caraterOpcao = sc.nextLine();
            if (caraterOpcao.equals("1")) {
            carater = CaraterDisciplina.OBRIGATORIA;
            } else if (caraterOpcao.equals("2")) {
            carater = CaraterDisciplina.OPTATIVA;
            }
            System.out.println();

            continuar = true;
            do{
                System.out.println("Carga Horaria:");
                System.out.println(cargas[0]);
                System.out.println(cargas[1]);
                System.out.println(cargas[2]);
                System.out.println(cargas[3]);

                System.out.println();
                System.out.print("Escolha a carga horaria da disciplina: ");
                String cargaOpcao = sc.nextLine();

                if (cargaOpcao.equals("1")) {
                    cargaHoraria = CargaHoraria.HORAS_32;
                    continuar = false;
                } else if (cargaOpcao.equals("2")) {
                    cargaHoraria = CargaHoraria.HORAS_64;
                    continuar = false;
                } else if (cargaOpcao.equals("3")) {
                    cargaHoraria = CargaHoraria.HORAS_96;
                    continuar = false;
                } else if (cargaOpcao.equals("4")) {
                    cargaHoraria = CargaHoraria.HORAS_160;
                    continuar = false;
                } else{
                    System.out.println("\nOpcao invalida!\n");
                }
            } while(continuar);

            System.out.println();
            System.out.println("Digite os requisitos da disciplina (se houver): ");
            requisitos = sc.nextLine();
            System.out.println("Digite a justificativa da disciplina: ");
            justificativa = sc.nextLine();
            System.out.println("Digite a ementa da disciplina: ");
            ementa = sc.nextLine();
            System.out.println("Digite os objetivos da disciplina: ");
            objetivos = sc.nextLine();

            int contAtividades = 1;
            System.out.println("\n======== Criacao do Calendario de Atividades =======");
            do{

                System.out.println("\n---- Atividade " + contAtividades + " / Faltam " + (cargaHoraria.getHoras() - horasCriadas) + " horas ----\n");

                System.out.print("Data[DD/MM]: ");
                String data = sc.nextLine();
                System.out.print("Descricao: ");
                String descricao = sc.nextLine();
                System.out.print("Carga Horaria: ");
                int tempo = sc.nextInt();
                sc.nextLine(); // corrige erro de pular algumas entradas

                if(data == null || data.isEmpty() || descricao == null || descricao.isEmpty()){
                    System.out.println("\nData ou descricao estao vazias! Refaca a atividade.");
                    continue;
                }

                if(horasCriadas + tempo > cargaHoraria.getHoras()){
                    System.out.println("\nA quantidade de horas ultrapassou o limite. Refaca a ultima atividade." +
                                        "\nSe voce errou em uma ativade anterior e possivel editar posteriormente. ");

                    continue;
                }

                datas.add(data);
                descricoes.add(descricao);
                horas.add(tempo);

                horasCriadas += tempo;
                contAtividades++;
            } while(horasCriadas < cargaHoraria.getHoras());

            System.out.println("\nDigite a metodologia da disciplina: ");
            metodologia = sc.nextLine();
            System.out.println("Digite as atividades discentes: ");
            atividadesDiscentes = sc.nextLine();
            System.out.println("Informe o sistema de avaliacao da disciplina: ");
            sistemaAvaliacao = sc.nextLine();
            System.out.println("Informe a bibliografia da disciplina: ");
            bibliografia = sc.nextLine();

            System.out.println(
                "\nCurso: " + curso +
                "\nNome: " + disciplina +
                "\nCodigo: " + codigo +
                "\nCarater: " + carater +
                "\nCarga Horaria: " + cargaHoraria.getHoras() + " horas" +
                "\nRequisitos: " + requisitos +
                "\nJustificativa: " + justificativa +
                "\nEmenta: " + ementa +
                "\nObjetivos: " + objetivos
            );

            System.out.println("\n======== Calendario de Atividades =======");
            System.out.println();
            for(int i = 0; i < datas.size(); i++){ // tamanho de datas == quantidade de atividades
                System.out.println(datas.get(i) + " - " + descricoes.get(i) + " - " + horas.get(i) + " horas");
            }

            System.out.println(
                "\nMetodologia: " + metodologia +
                "\nAtividades discentes: " + atividadesDiscentes +
                "\nSistema de Avaliacao: " + sistemaAvaliacao +
                "\nBibliografia: " + bibliografia
            );

            System.out.println("\nOs dados estao corretos? (S/N)");
            resposta = sc.nextLine().trim().toUpperCase();
        } while (resposta.equals("N"));
        controller.handleEvent("OK");
        model.detachObserver(this);
    }

    //gets das informacoes do calendario de atividades
    public ArrayList<String> getDatas() {return datas;}
    public ArrayList<String> getDescricoes() {return descricoes;}
    public ArrayList<Integer> getHoras() {return horas;}

    public String getCurso() {
        return curso;
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
