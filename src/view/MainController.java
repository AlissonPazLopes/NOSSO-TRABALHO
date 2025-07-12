package view;

import model.*;

public class MainController implements Observer {
    private Model model;
    private MainView view;

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
                LoginView viewL = new LoginView();
                //viewL.init(model);
                break;
            case "2":
                RegisterView viewR = new RegisterView();
                //viewR.init(model);
                break;
            case "3":
                view.finalizarSistema();
                break;
        }
    }
}
