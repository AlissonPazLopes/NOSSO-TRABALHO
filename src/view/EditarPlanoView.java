package view;

import java.util.*;
import model.*;

public class EditarPlanoView implements Observer {
    private Model model;
    private EditarPlanoController controller;

    public void init(Model model) {
        this.model = model;
        controller = new EditarPlanoController();
        controller.init(model, this);
        model.attachObserver(this);
        editarPlano();
    }

    public void editarPlano() {

    }

    public void update() {
        
    }
}
