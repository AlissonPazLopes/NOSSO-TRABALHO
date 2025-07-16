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

    public void mudarPerfil() {
        model.setUsuario(usuario);
        PerfilView view = new PerfilView();
        view.init(model);
    }

    public void handleEvent(int event) {
        Scanner sc = new Scanner(System.in);
        switch(event) {
            case 1:
                if(model.getUsuarioAutenticado() instanceof Aluno){
                    HomeAlunoView view = new HomeAlunoView();
                    view.init(model);
                    break;
                } else if(model.getUsuarioAutenticado() instanceof Professor) {
                    HomeProfessorView view = new HomeProfessorView();
                    view.init(model);
                    break;
                }
            case 2:
                this.view.exibirMsg("Novo nome: ");
                String nome = sc.nextLine();
                model.removeUsuario(usuario.getLogin());
                usuario.setNome(nome);
                mudarPerfil();
                break;
            case 3:
                this.view.exibirMsg("Novo login: ");
                String login = sc.nextLine();
                model.removeUsuario(usuario.getLogin());
                usuario.setLogin(login);
                mudarPerfil();
                break;
            case 4:
                this.view.exibirMsg("Nova senha: ");
                String senha = sc.nextLine();
                model.removeUsuario(usuario.getLogin());
                usuario.setSenha(senha);
                mudarPerfil();
                break;
            case 5:
                if(usuario instanceof Aluno){
                    Aluno u = (Aluno) usuario;
                    this.view.exibirMsg("Nova matricula: ");
                    int matricula = sc.nextInt();
                    model.removeUsuario(usuario.getLogin());
                    u.setMatricula(matricula);
                    mudarPerfil();
                    break;
                } else break;
            case 6:
                if(usuario instanceof Aluno){
                    Aluno u = (Aluno) usuario;
                    this.view.exibirMsg("Novo curso: ");
                    String curso = sc.nextLine();
                    model.removeUsuario(usuario.getLogin());
                    u.setCurso(curso);
                    mudarPerfil();
                    break;
                } else break;
        }

        if(usuario instanceof Professor && event >= 5) {
            Professor u = (Professor) usuario;
            if(event == u.getChaveAddDisciplina()){
                view.exibirMsg("Escreva o nome da disciplina: ");
                String nome = sc.nextLine();
                view.exibirMsg("Escreva o codigo da disciplina: ");
                String codigo = sc.nextLine();
                model.removeUsuario(usuario.getLogin());
                u.setDisciplina(new Disciplina(nome, codigo));
                model.setUsuario(u);
                PerfilView view = new PerfilView();
                view.init(model);
            } else {
                int i = event - 4;
                int j = 1;
                for (Disciplina disciplina : u.getDisciplinas().values()) {
                    if (j == i) {
                        u.removeDisciplina(disciplina);
                        model.removeUsuario(usuario.getLogin());
                        model.setUsuario(u);
                        PerfilView view = new PerfilView();
                        view.init(model);
                        break;
                    }
                    j++;
                }
            }
        }

    }

    public void update() {}
}
