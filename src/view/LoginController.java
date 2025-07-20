package view;

import model.Model;
import model.Usuario;
import model.Aluno;
import model.Professor;

public class LoginController implements Observer{
    private Model model;
    private LoginView view;

    public void init(Model model, LoginView view) {
        if(model != null && view != null) {
            this.model = model;
            this.view = view;


            // aqui ele desregistra o observer porque a tela de login não vai mais ser usada nessa sessão.
            model.attachObserver(this);
        }
    }

    public void handleEvent(String event) {
        switch(event) {
            case "OK":

                //aqui vai solicitar autenticação com o model para os dados da view
                boolean autenticado = model.autenticarUsuario(view.getLogin(), view.getSenha());
                if(!autenticado) {
                    view.exibirMsg("ERRO: Login ou senha invalidos.");
                } else {
                    view.exibirMsg("Login realizado com sucesso!");
                    Usuario usuario = model.getUsuarioAutenticado();


                    // Verifica aqui o tipo de usuário para saber qual tela  vai exibir
                    if(usuario instanceof Aluno) {
                        HomeAlunoView viewHA = new HomeAlunoView();
                        viewHA.init(model);
                    } else if(usuario instanceof Professor) {
                        HomeProfessorView viewHP = new HomeProfessorView();
                        viewHP.init(model);
                    }
                }
                model.detachObserver(this);
                break;
        }
    }

    public void update() {
        
    }
    
}
