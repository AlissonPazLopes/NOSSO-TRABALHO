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
        int opcoesBase = (usuario instanceof Aluno) ? 6 : 4;
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
                String nome = view.solicitarNovoNome();
                usuario.setNome(nome);
                model.atualizaUsuario(usuario);
                view.verPerfil();
                break;
            case 2:
                String login = view.solicitarNovoLogin();
                String loginAntigo = usuario.getLogin();
                usuario.setLogin(login);
                model.atualizaUsuario(usuario, loginAntigo);
                view.verPerfil();
                break;
            case 3:
                String senha = view.solicitarNovaSenha();
                usuario.setSenha(senha);
                model.atualizaUsuario(usuario);
                view.verPerfil();
                break;
            case 4:
                if(usuario instanceof Aluno){
                    Aluno u = (Aluno) usuario;
                    int matricula = view.solicitarNovaMatricula();
                    u.setMatricula(matricula);
                    model.atualizaUsuario(u);
                    view.verPerfil();
                } 
                break;
            case 5:
                if(usuario instanceof Aluno){
                    Aluno u = (Aluno) usuario;
                    String curso = view.solicitarNovoCurso();
                    u.setCurso(curso);
                    model.atualizaUsuario(u);
                    view.verPerfil();
                } 
                break;
            default:
                if(!(usuario instanceof Professor && event >= opcoesBase)) {
                    view.exibirMsg("Escolha uma opcao valida!\n\n");
                    view.verPerfil();
                }
                break;
        }

        if(usuario instanceof Professor && event >= opcoesBase) {
            Professor u = (Professor) usuario;
            int indiceAdicionar = opcoesBase + u.getTotalDisciplinas();
            if(event == indiceAdicionar) {
                String nome = view.solicitarNomeDisciplina();
                String codigo = view.solicitarCodigoDisciplina();
                u.setDisciplina(nome, codigo);
                model.atualizaUsuario(u);
            } else {
                int indiceRemover = event - opcoesBase;
                int c = 0;
                for (Disciplina disciplina : u.getDisciplinas().values()) {
                    if (c == indiceRemover) {
                        u.removeDisciplina(disciplina);
                        model.atualizaUsuario(u);
                        break;
                    }
                    c++;
                }
            }
            view.verPerfil();
        }

    }

    public void update() {}
}
