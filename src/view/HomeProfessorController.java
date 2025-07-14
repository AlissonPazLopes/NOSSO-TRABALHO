package view;

import model.Model;

public class HomeProfessorController implements Observer {
    private Model model;
    private HomeProfessorView view;

    public void init(Model model, HomeProfessorView view) {
        if(model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void handleEvent(String event) {
        switch(event) {
            case "1":
                view.exibirMsg("Consultando Planos de Ensino...");
                ConsultaView viewC = new ConsultaView();
                viewC.init(model);
                break;
            case "2":
                CriarPlanoView viewCP = new CriarPlanoView();
                viewCP.init(model);
                break;
            case "3":
                view.exibirMsg("Verificando Planos de Ensino Criados...");
                PlanosCriadosView viewPC = new PlanosCriadosView();
                viewPC.init(model);
                break;
            case "4":
                view.exibirMsg("Exibindo Perfil do Professor...");
                PerfilView viewP = new PerfilView();
                viewP.init(model);
                break;
            case "5":
                view.exibirMsg("Realizando Logout...");
                model.deslogarUsuario();
                model.detachObserver(this);
                break;
        }
    }

    public void update() {

    }
    
}
