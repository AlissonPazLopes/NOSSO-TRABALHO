package view;

import model.Model;

public class SugestoesController implements Observer {
    private Model model;
    private SugestoesView view;

    public void init(Model model, SugestoesView view) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void update() {}
}
