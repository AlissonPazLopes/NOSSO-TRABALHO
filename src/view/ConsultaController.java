package view;

import model.Model;
import java.util.ArrayList;
import model.Ped;
import model.Usuario;
import model.Professor;
import model.Aluno;

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
                view.setUltimaBusca(encontrados);
                view.exibirResultados(encontrados);
                break;
            case "VOLTAR":
                model.detachObserver(this);
                Usuario usuario = model.getUsuarioAutenticado();
                if(usuario instanceof Professor) {
                    HomeProfessorView viewHP = new HomeProfessorView();
                    viewHP.init(model);
                } else if(usuario instanceof Aluno) {
                    HomeAlunoView viewHA = new HomeAlunoView();
                    viewHA.init(model);
                }    
            default:
                if(event.startsWith("DETALHES:")) {
                    int index = Integer.parseInt(event.split(":")[1]);
                    Ped pedEscolhido = view.getUltimaBusca().get(index);
                    view.exibirDetalhes(pedEscolhido);
                }
                break;
        }
    }

    public void update() {
        
    }
}
