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
            for(int i = 0; i <= professor.getTotalDisciplinas(); i++){
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

    public void encerrar() {model.detachObserver(this);}

    public void update() {}
}
