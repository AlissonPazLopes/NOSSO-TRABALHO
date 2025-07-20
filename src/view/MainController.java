package view;

import model.*;






public class MainController implements Observer {
    private Model model;
    private MainView view;


    // inicia o controlle e estabelece  uma conexão com o Model e a View.

    public void init(Model model, MainView view) {
        if(model != null && view != null) {
            this.model = model;
            this.view = view;
        }
    }

    public void update() {

    }

    public void handleEvent(String event) {
        switch(event) {
            case "1":
                //navega para a "tela" de login
                LoginView viewL = new LoginView();
                viewL.init(model);
                break;
            case "2":
                // vai para a tela de registrar-se
                RegisterView viewR = new RegisterView();
                viewR.init(model);
                break;
            case "3":
                //aqui ele sinaliza para finalizar o programa!
                view.finalizarSistema();
                break;
        }
    }
}
