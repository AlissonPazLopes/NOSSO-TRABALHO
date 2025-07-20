package view;

import model.*;
import java.util.ArrayList;


// esse controller vai para a tela de perfil a PerfilView
// que lida com a visualização e principalmente
// a edição dos dados do usuário autenticado que é o Aluno ou o Professor





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
        // Define o número de opções base para diferenciar Aluno e o  Professor
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
                String senhaAtual = view.solicitarSenhaAtual();
                if(!usuario.getSenha().equals(senhaAtual)) {
                    view.exibirMsg("\nSenha atual incorreta!\n");
                    view.verPerfil();
                    return;
                }
                String novaSenha = view.solicitarNovaSenha();
                usuario.setSenha(novaSenha);
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
        // basicamente esse trecho específica para Professor (Adicionar ou Remover Disciplina.
        if(usuario instanceof Professor && event >= opcoesBase) {
            Professor u = (Professor) usuario;
            int indiceAdicionar = opcoesBase + u.getTotalDisciplinas();
            // Adicionar uma nova disciplina
            if(event == indiceAdicionar) {
                String nome = view.solicitarNomeDisciplina();
                String codigo = view.solicitarCodigoDisciplina();
                u.setDisciplina(nome, codigo);
                model.atualizaUsuario(u);
            } else {
                //remover a disciplina existente
                int indiceRemover = event - opcoesBase;
                int c = 0;
                Disciplina disciplinaRemover = null;
                // para encontrar a disciplina a ser removida com base no índice da opção
                for (Disciplina disciplina : u.getDisciplinas().values()) {
                    if (c == indiceRemover) {
                        disciplinaRemover = disciplina;
                        break;
                    }
                    c++;
                }
                if(disciplinaRemover != null) {
                    String confirmacao = view.confirmarRemocaoDisciplina(disciplinaRemover.getNome());
                    if(confirmacao.equalsIgnoreCase("S")) {
                        u.removeDisciplina(disciplinaRemover);

                        //aqui remove os PEDs associados à disciplina removida
                        ArrayList<Ped> pedsParaRemover = new ArrayList<>();
                        for(Ped ped : model.getPeds()) {
                            if(ped.getProfessor().equals(u) && 
                               ped.getDisciplina().getCodigo().equals(disciplinaRemover.getCodigo())) {
                                pedsParaRemover.add(ped);
                            }
                        }
                        for(Ped ped : pedsParaRemover) {
                            model.removePed(ped);
                        }
                        model.atualizaUsuario(u);
                        view.exibirMsg("Disciplina removida com sucesso!\n");
                    } else {
                        view.exibirMsg("Remoção cancelada.\n");
                    }
                }
            }
            view.verPerfil(); // recarrega a tela de perfil no final da operação
        }

    }

    public void update() {}
}
