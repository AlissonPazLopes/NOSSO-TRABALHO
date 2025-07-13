package view;

import java.util.*;
import model.*;

public class ConsultaView implements Observer {
    private Model model;
    private ConsultaController controller;

    public void init(Model model) {
        this.model = model;
        this.controller = new ConsultaController();
        controller.init(model, this);
        model.attachObserver(this);
        menuConsulta();
    }

    public void menuConsulta() {
        Scanner sc = new Scanner(System.in);
    }
}
