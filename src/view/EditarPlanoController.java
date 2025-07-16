package view;

import model.Model;

public class EditarPlanoController implements Observer {
    private Model model;
    private EditarPlanoView view;
    
    public void init(Model model, EditarPlanoView view) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void update() {

    }
}
