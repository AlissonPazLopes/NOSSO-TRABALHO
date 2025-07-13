package view;

import model.Model;

public class HomeAlunoController implements Observer {
    private Model model;
    private HomeAlunoView view;

    public void init(Model model, HomeAlunoView view) {
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
                view.exibirMsg("Verificando Planos de Ensino Sugeridos...");
                SugestoesView viewS = new SugestoesView();
                viewS.init(model);
                break;
            case "3":
                view.exibirMsg("Exibindo Perfil do Aluno...");
                PerfilView viewP = new PerfilView();
                viewP.init(model);
                break;
            case "4":
                view.exibirMsg("Realizando Logout...");
                model.deslogarUsuario();
                MainView viewM = new MainView();
                viewM.init(model);
                break;
        }
    }
}
