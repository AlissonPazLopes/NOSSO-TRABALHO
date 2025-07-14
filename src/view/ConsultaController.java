package view;

public class ConsultaController implements Observer {
    private Model model;
    private ConsultaView view;

    public void init(Model model, ConsultaView view) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }
}
