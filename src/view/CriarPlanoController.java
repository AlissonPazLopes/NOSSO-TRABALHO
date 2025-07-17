package view;

import model.*;

import java.util.ArrayList;

public class CriarPlanoController implements Observer {
    private Model model;
    private CriarPlanoView view;
    private ArrayList<Atividade> calendario;

    public void init(Model model, CriarPlanoView view) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void handleEvent(String event) {
        switch(event) {
            case "OK":
                this.calendario = model.criarCalendario(view.getDatas(), view.getDescricoes(), view.getHoras());
                Professor professor = (Professor) model.getUsuarioAutenticado();
                model.cadastrarPed(
                    view.getCodigo(),
                    professor,
                    view.getDisciplina(),
                    view.getCarater(),
                    view.getCargaHoraria(),
                    view.getRequisitos(), 
                    view.getJustificativa(), 
                    view.getEmenta(),
                    view.getObjetivos(),
                    this.calendario,
                    view.getMetodologia(), 
                    view.getAtividadesDiscentes(),
                    view.getSistemaAvaliacao(), 
                    view.getBibliografia()
                );
                view.exibirMsg("Plano criado com sucesso!");
                HomeProfessorView viewHP = new HomeProfessorView();
                viewHP.init(model);
                model.detachObserver(this);
                break;
        }
    }

    public void update() {
        
    }
}
