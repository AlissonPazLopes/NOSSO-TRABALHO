package view;

import model.Model;
import java.util.ArrayList;
import model.Ped;

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

    public void handleEvent(String event) {
        switch(event) {
            case "OK":
                String termo = view.getBusca();
                ArrayList<Ped> encontrados = new ArrayList<>();
                for(Ped ped : model.getPeds()) {
                    if(ped.getDisciplina().getNome().toLowerCase().contains(termo)) {
                        encontrados.add(ped);
                    }
                }
                view.exibirResultados(encontrados);
                break;
        }
    }

    public void update() {
        
    }
}
