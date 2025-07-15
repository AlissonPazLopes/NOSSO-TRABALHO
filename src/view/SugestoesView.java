package view;

import model.*;

public class SugestoesView implements Observer {
    private Model model;
    private SugestoesController controller;

    public void init(Model model) {
        this.model = model;
        this.controller = new SugestoesController();
        controller.init(model, this);
        model.attachObserver(this);
    }

    public void update() {}
}
