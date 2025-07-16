package view;

import model.Model;
import model.Professor;
import model.Disciplina;
import model.Ped;

public class CriarPlanoController implements Observer {
    private Model model;
    private CriarPlanoView view;

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
                    view.getMetodologia(), 
                    view.getAtividadesDiscentes(),
                    view.getSistemaAvaliacao(), 
                    view.getBibliografia()
                );
                view.exibirMsg("Plano criado com sucesso! Para adicionar o calendario de atividades, va em 'Ver Planos de Ensino Criados'");
                HomeProfessorView viewHP = new HomeProfessorView();
                viewHP.init(model);
                model.detachObserver(this);
                break;
        }
    }

    public void update() {
        
    }
}
