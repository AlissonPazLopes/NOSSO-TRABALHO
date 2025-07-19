package view;

import model.*;

import java.util.Scanner;

public class PerfilController implements Observer {
    private Model model;
    private PerfilView view;
    private Usuario usuario;

    public void init(Model model, PerfilView view) {
        if(model != null && view != null) {
            this.model = model;
            this.view = view;
            this.usuario = model.getUsuarioAutenticado();
            model.attachObserver(this);
        }
    }

    public void handleEvent(int event) {
        Scanner sc = new Scanner(System.in);
        switch(event) {
            case 0:
                if(model.getUsuarioAutenticado() instanceof Aluno){
                    model.detachObserver(this);
                    view.encerrar();
                    HomeAlunoView view = new HomeAlunoView();
                    view.init(model);
                    break;
                } else if(model.getUsuarioAutenticado() instanceof Professor) {
                    model.detachObserver(this);
                    view.encerrar();
                    HomeProfessorView view = new HomeProfessorView();
                    view.init(model);
                    break;
                }
            case 1:
                this.view.exibirMsg("Novo nome: ");
                String nome = sc.nextLine();
                usuario.setNome(nome);
                model.atualizaUsuario(usuario);
                view.verPerfil();
                break;
            case 2:
                this.view.exibirMsg("Novo login: ");
                String login = sc.nextLine();
                String loginAntigo = usuario.getLogin();
                usuario.setLogin(login);
                model.atualizaUsuario(usuario, loginAntigo);
                view.verPerfil();
                break;
            case 3:
                this.view.exibirMsg("Nova senha: ");
                String senha = sc.nextLine();
                usuario.setSenha(senha);
                model.atualizaUsuario(usuario);
                view.verPerfil();
                break;
            case 4:
                if(usuario instanceof Aluno){
                    Aluno u = (Aluno) usuario;
                    this.view.exibirMsg("Nova matricula: ");
                    int matricula = sc.nextInt();
                    u.setMatricula(matricula);
                    model.atualizaUsuario(u);
                    view.verPerfil();
                    break;
                } else break;
            case 5:
                if(usuario instanceof Aluno){
                    Aluno u = (Aluno) usuario;
                    this.view.exibirMsg("Novo curso: ");
                    String curso = sc.nextLine();
                    u.setCurso(curso);
                    model.atualizaUsuario(u);
                    view.verPerfil();
                    break;
                } else break;
            default:
                view.exibirMsg("Escolha uma opcao valida!\n\n");
                view.verPerfil();
                break;
        }

        if(usuario instanceof Professor && event >= 4) {
            Professor u = (Professor) usuario;
            if(event == u.getChaveAddDisciplina()){
                view.exibirMsg("Escreva o nome da disciplina: ");
                String nome = sc.nextLine();
                view.exibirMsg("Escreva o codigo da disciplina: ");
                String codigo = sc.nextLine();
                u.setDisciplina(nome, codigo);
                model.atualizaUsuario(u);
            } else {
                int i = event - 3;
                int j = 1;
                for (Disciplina disciplina : u.getDisciplinas().values()) {
                    if (j == i) {
                        u.removeDisciplina(disciplina);
                        model.atualizaUsuario(u);
                        break;
                    }
                    j++;
                }
            }
            view.verPerfil();
        }

    }

    public void update() {}
}
