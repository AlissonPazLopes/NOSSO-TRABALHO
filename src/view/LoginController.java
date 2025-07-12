package view;

import model.Model;
import model.Usuario;

public class LoginController implements Observer{
    private Model model;
    private LoginView view;

    public void init(Model model, LoginView view) {
        if(model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void handleEvent(String event) {
        switch(event) {
            case "OK":
                boolean autenticado = model.autenticarUsuario(view.getLogin(), view.getSenha());
                if(!autenticado) {
                    view.exibirMsg("ERRO: Login ou senha invalidos.");
                } else {
                    view.exibirMsg("Login realizado com sucesso!");
                }
                model.detachObserver(this);
                break;
        }
    }

    public void update() {
        
    }
    
}
