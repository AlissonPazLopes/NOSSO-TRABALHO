package view;

import java.util.*;
import model.*;
import model.exception.SenhaInvalidaException;

public class RegisterView implements Observer {
    private Model model;
    private RegisterController controller;
    private String nome;
    private String login;
    private String senha;
    private String curso;
    private String matricula;
    private ArrayList<Disciplina> disciplinas = new ArrayList<>();
    private String tipoUsuario;

    //inicializa a view com o model e controller
    public void init(Model model) {
        this.model = model;
        controller = new RegisterController();
        controller.init(model, this);
        model.attachObserver(this);
        cadastrarUsuario();
    }

    // Vai controlar o fluxo de cadastro dos usuários
    public void cadastrarUsuario() {
        Scanner sc = new Scanner(System.in);
        String[] opcoes = {"1 - Aluno", "2 - Professor"};

        System.out.println();
        System.out.println("""
        ================================================
                           Cadastro
        ================================================
        """);
        //vai exbiir o tipo de usuário
        System.out.println(opcoes[0]);
        System.out.println(opcoes[1]);
        System.out.print("\nEscolha o tipo de usuario: ");
        tipoUsuario = sc.nextLine();

        //cadastro do aluno
        if(tipoUsuario.equals("1")) {
            String[] cursos = {
                "CC - Ciencia da computacao",
                "ES - Engenharia de Software",
                "EC - Engenharia Civil",
                "EM - Engenharia Mecânica",
                "EP - Engenharia de Produção"
            };
            System.out.println("\nCursos:");
            for(String c : cursos) {
                System.out.println(c);
            }
            System.out.print("\nEscolha a sigla do seu curso: ");
            curso = sc.nextLine().toUpperCase();

            while(true) {
                System.out.print("Digite sua matricula: ");
                matricula = sc.nextLine();
                try {
                    int mat = Integer.parseInt(matricula);
                    if(mat >= 100000 && mat <= 999999) {
                        if(model.getAlunoPorMatricula(matricula) != null) {
                            System.out.println("\nJá existe um aluno cadastrado com essa matrícula!\n");
                            model.detachObserver(this);
                            return;
                        }
                        break; // Se a matrícula for válida, sai do loop
                    } else {
                        System.out.println("A matrícula deve ser um número entre 100000 e 999999.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Digite apenas números para a matrícula.");
                }
            }
            

            // Verifica se a matrícula já foi cadastrada
            if(model.getAlunoPorMatricula(matricula) != null) {
                System.out.println("\nJá existe um aluno cadastrado com essa matricula!\n");
                model.detachObserver(this);
                return;
            }

            //Cadastro do professor
        } else if(tipoUsuario.equals("2")) {
            String resposta;
            do {
                // Pega o nome e o código de cada disciplina
                System.out.print("Digite o nome da disciplina ministrada: ");
                String disciplinaNome = sc.nextLine();
                System.out.print("Digite o código da disciplina: ");
                String disciplinaCodigo = sc.nextLine();
                disciplinas.add(new Disciplina(disciplinaNome, disciplinaCodigo));
                System.out.print("Deseja adicionar mais uma disciplina? (S/N): ");
                resposta = sc.nextLine().trim().toLowerCase();
            } while(resposta.equals("s") || resposta.equals("sim"));
        }

        //Se tipo de usuário for válido, ele vai continuar o cadastro
        if(tipoUsuario.equals("1") || tipoUsuario.equals("2")) {
            System.out.print("Digite seu nome: ");
            nome = sc.nextLine();
            System.out.print("Digite seu login: ");
            login = sc.nextLine();

            if(model.getUsuario(login) != null) {
                System.out.println("\nJá existe um usuário cadastrado com esse login!\n");
                model.detachObserver(this);
                return;
            }

            while (true) { // Loop infinito que só para com o 'break'
                System.out.print("Digite sua senha: ");
                this.senha = sc.nextLine();
                try {
                    controller.handleEvent("OK");
                    // Se chegou aqui, a exceção não foi lançada, então o cadastro foi um sucesso
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                } catch (SenhaInvalidaException e) {
                    // Pega o erro específico da senha, mostra a mensagem e continua o loop
                    System.out.println(" Erro: " + e.getMessage());
                    System.out.println("Por favor, tente novamente.");
                } catch (Exception e) {
                    // Pega qualquer outro erro inesperado para não quebrar o programa
                    System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                    break;

                }
            }
            model.detachObserver(this);
        } else {
            System.out.println("\nEscolha uma opção válida!\n");
            model.detachObserver(this);
        }
    }


    //Getters para acessar os dados digitados
    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getCurso() {
        return curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void update() {}

}
