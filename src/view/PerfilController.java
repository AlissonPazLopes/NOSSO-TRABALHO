package view;

import model.*;

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

    public void handleEvent(String event) {
        switch(event) {
            case "OK":
                return;


        }
    }

    public void update() {}
}
