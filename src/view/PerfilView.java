package view;

import java.util.*;
import model.*;

public class PerfilView implements Observer {
    private Model model;
    private PerfilController controller;
    private Usuario usuarioAutenticado;

    public void init(Model model) {
        this.model = model;
        controller = new PerfilController();
        usuarioAutenticado = model.getUsuarioAutenticado();
        controller.init(model, this);
        model.attachObserver(this);
        verPerfil();
    }

    public void verPerfil() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("0 - Sair");
        opcoes.add("1 - Nome");
        opcoes.add("2 - Login");
        opcoes.add("3 - Senha");
        if(usuarioAutenticado instanceof Aluno) {opcoes.add("4 - Matricula");
                                                 opcoes.add("5 - Curso");}

        else{
            Professor professor = (Professor) usuarioAutenticado;
            int proximoNumeroOpcao = opcoes.size();
            int index = 0;
            for(Disciplina disciplina : professor.getDisciplinas().values()) {
                opcoes.add((proximoNumeroOpcao + index) + " - Remover disciplina " + disciplina.getNome());
                index++;
            }
            opcoes.add((proximoNumeroOpcao + index) + " - Adicionar disciplina");
        }

        System.out.println("""
        ================================================
                           Perfil
        ================================================
        """);

        System.out.println("Nome: " + usuarioAutenticado.getNome());
        System.out.println("Login: " + usuarioAutenticado.getLogin());

        String senhaOculta = "*".repeat(usuarioAutenticado.getSenha().length());
        System.out.println("Senha: " + senhaOculta);

        if(usuarioAutenticado instanceof Aluno) {
            Aluno aluno = (Aluno) usuarioAutenticado;
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Curso: " + aluno.getCurso());
        } else if(usuarioAutenticado instanceof Professor) {
            Professor professor = (Professor) usuarioAutenticado;
            System.out.println("Disciplinas:");
            for(Disciplina d : professor.getDisciplinas().values()) {
                System.out.println("- " + d.getNome() + " (" + d.getCodigo() + ")");
            }
        }

        System.out.println("\nEscolha: [Sair / Mudar um dado]\n");

        for(String opcao : opcoes) {System.out.println(opcao);}

        System.out.print("\nOpcao: ");
        int event = sc.nextInt();

        controller.handleEvent(event);
        model.detachObserver(this);
    }

    public void exibirMsg(String msg) {
        System.out.println();
        System.out.print(msg);
    }

    public String solicitarNovoNome() {
        System.out.print("Novo nome: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String solicitarNovoLogin() {
        System.out.print("Novo login: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String solicitarSenhaAtual() {
        System.out.print("Digite sua senha atual: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String solicitarNovaSenha() {
        System.out.print("Nova senha: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public int solicitarNovaMatricula() {
        System.out.print("Nova matrícula: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public String solicitarNovoCurso() {
        System.out.print("Novo curso: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String solicitarNomeDisciplina() {
        System.out.print("Nome da disciplina: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String solicitarCodigoDisciplina() {
        System.out.print("Código da disciplina: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void encerrar() {model.detachObserver(this);}

    public void update() {}
}
