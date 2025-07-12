package view;

import java.util.*;
import model.*;

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

    public void init(Model model) {
        this.model = model;
        controller = new RegisterController();
        controller.init(model, this);
        model.attachObserver(this);
        cadastrarUsuario();
    }

    public void cadastrarUsuario() {
        Scanner sc = new Scanner(System.in);
        String[] opcoes = {"1 - Aluno", "2 - Professor"};

        System.out.println("""
        ================================================
                           Cadastro
        ================================================
        """);
        
        System.out.println(opcoes[0]);
        System.out.println(opcoes[1]);
        System.out.print("Escolha o tipo de usuario: ");
        tipoUsuario = sc.nextLine();
        if(tipoUsuario.equals("1")) {
            String[] cursos = {
                "CC - Ciencia da computacao",
                "ES - Engenharia de Software",
                "EC - Engenharia Civil",
                "EM - Engenharia Mecânica",
                "EP - Engenharia de Produção"
            };
            for(String c : cursos) {
                System.out.println(c);
            }
            System.out.print("Escolha a sigla do seu curso: ");
            curso = sc.nextLine();

            System.out.print("Digite sua matricula: ");
            matricula = sc.nextLine();
        } else if(tipoUsuario.equals("2")) {
            String resposta;
            do {
                System.out.print("Digite o nome da disciplina ministrada: ");
                String disciplinaNome = sc.nextLine();
                System.out.print("Digite o código da disciplina: ");
                String disciplinaCodigo = sc.nextLine();
                disciplinas.add(new Disciplina(disciplinaNome, disciplinaCodigo));
                System.out.print("Deseja adicionar mais uma disciplina? (S/N): ");
                resposta = sc.nextLine().trim().toLowerCase();
            } while(resposta.equals("s") || resposta.equals("sim"));
        }
        System.out.print("Digite seu nome: ");
        nome = sc.nextLine();
        System.out.print("Digite seu login: ");
        login = sc.nextLine();
        System.out.print("Digite sua senha: ");
        senha = sc.nextLine();
        controller.handleEvent("OK");

        model.detachObserver(this);
    }

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

    public void update() {
        
    }

}
