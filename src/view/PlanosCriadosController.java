package view;

import java.util.ArrayList;
import model.Model;
import model.Ped;
import model.Professor;

public class PlanosCriadosController implements Observer {
    private Model model;
    private PlanosCriadosView view;
    
    public void init(Model model, PlanosCriadosView view) {
        if(model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void handleEvent(String event) {
        switch(event) {
            case "VOLTAR":
                model.detachObserver(this);
                HomeProfessorView viewHP = new HomeProfessorView();
                viewHP.init(model);
                break;
            default:
                if(event.startsWith("DETALHES:")) {
                    int index = Integer.parseInt(event.split(":")[1]);
                    Professor professor = (Professor) model.getUsuarioAutenticado();
                    ArrayList<Ped> pedsProfessor = new ArrayList<>();
                    for(Ped ped : model.getPeds()) {
                        if(ped.getProfessor().equals(professor)) {
                            pedsProfessor.add(ped);
                        }
                    }
                    if(index >= 0 && index < pedsProfessor.size()) {
                        Ped pedEscolhido = pedsProfessor.get(index);
                        view.exibirDetalhes(pedEscolhido);
                        view.opcoesPlano(pedEscolhido);
                    } else {
                        handleEvent("VOLTAR");
                    }
                } else if(event.startsWith("EDITAR:")) {
                    String codigoPed = event.split(":")[1].trim();              // <<< trim() aqui
                    Ped pedEscolhido = model.getPedPorCodigo(codigoPed);
                    if (pedEscolhido == null) {
                        view.exibirMsg("Erro: não encontrei o plano com código “" + codigoPed + "”.");
                        return;
                    }
                    model.detachObserver(this);
                    EditarPlanoView viewEP = new EditarPlanoView();
                    viewEP.init(model, pedEscolhido);
                } else if(event.startsWith("REMOVER:")) {
                    String codigo = event.split(":")[1];
                    Professor professor = (Professor) model.getUsuarioAutenticado();
                    Ped pedRemover = null;
                    for(Ped ped : model.getPeds()) {
                        if(ped.getDisciplina().getCodigo().equals(codigo) && ped.getProfessor().equals(professor)) {
                            pedRemover = ped;
                            break;
                        }
                    }
                    if(pedRemover != null && view.confirmarRemocao(pedRemover, professor)) {
                        model.getPeds().remove(pedRemover);
                        view.exibirMsg("Plano removido com sucesso!");
                        handleEvent("VOLTAR");
                    } else {
                        view.opcoesPlano(pedRemover);
                    }
                }
        }
    }

    public void update() {
        
    }
}
