package view;

import model.*;

import java.util.Scanner;

public class PerfilController implements Observer {
    private Model model;
    private PerfilView view;

    public void init(Model model, PerfilView view) {
        if(model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void handleEvent(int event) {
        Scanner sc = new Scanner(System.in);
        Usuario usuario = model.getUsuarioAutenticado();
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
                model.setUsuario(usuario);
                PerfilView viewN = new PerfilView();
                viewN.init(model);
                break;
            case 3:
                this.view.exibirMsg("Novo login: ");
                String login = sc.nextLine();
                model.removeUsuario(usuario.getLogin());
                usuario.setLogin(login);
                model.setUsuario(usuario);
                PerfilView viewL = new PerfilView();
                viewL.init(model);
                break;
            case 4:
                this.view.exibirMsg("Nova senha: ");
                String senha = sc.nextLine();
                model.removeUsuario(usuario.getLogin());
                usuario.setSenha(senha);
                model.setUsuario(usuario);
                PerfilView viewS = new PerfilView();
                viewS.init(model);
                break;
        }

    }

    public void update() {}
}
