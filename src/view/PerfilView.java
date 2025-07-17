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
        opcoes.add("1 - Sair");
        opcoes.add("2 - Nome");
        opcoes.add("3 - Login");
        opcoes.add("4 - Senha");
        if(usuarioAutenticado instanceof Aluno) {opcoes.add("5 - Matricula");
                                                 opcoes.add("6 - Curso");}

        else{
            Professor professor = (Professor) usuarioAutenticado;
            int proximoNumeroOpcao = opcoes.size() + 1;
            for(int i = 0; i < professor.getTotalDisciplinas() + 1; i++){
                if(i == professor.getTotalDisciplinas()) opcoes.add(proximoNumeroOpcao + " - Adicionar disciplina");
                else {
                    opcoes.add(proximoNumeroOpcao + " - Remover disciplina");
                    proximoNumeroOpcao++;
                }
            }
        }

        System.out.println("""
        ================================================
                           Perfil
        ================================================
        """);
        System.out.println(usuarioAutenticado);
        System.out.println("\nEscolha: [Sair / Mudar um dado]");

        for(String opcao : opcoes) {System.out.println(opcao);}

        System.out.println("\nOpcao: ");
        int event = sc.nextInt();

        controller.handleEvent(event);
        model.detachObserver(this);
    }

    public void exibirMsg(String msg) {
        System.out.println();
        System.out.println(msg);
    }

    public void update() {}
}
