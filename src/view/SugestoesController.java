package view;

import model.Model;
import model.Aluno;
import model.Ped;
import java.util.ArrayList;

public class SugestoesController implements Observer {
    private Model model;
    private SugestoesView view;

    public void init(Model model, SugestoesView view) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void handleEvent(String event) {
        switch(event) {
            case "VOLTAR":
                model.detachObserver(this);
                HomeAlunoView viewHA = new HomeAlunoView();
                viewHA.init(model);
                break;
            default:
                if(event.startsWith("DETALHES:")) {
                    int index = Integer.parseInt(event.split(":")[1]);
                    Aluno aluno = (Aluno) model.getUsuarioAutenticado();
                    String cursoAluno = aluno.getCurso();
                    ArrayList<Ped> sugestoes = new ArrayList<>();
                    for(Ped ped : model.getPeds()) {
                        if(ped.getCurso().equalsIgnoreCase(cursoAluno)) {
                            sugestoes.add(ped);
                        }
                    }
                    if(index >= 0 && index < sugestoes.size()) {
                        Ped pedEscolhido = sugestoes.get(index);
                        view.exibirDetalhes(pedEscolhido);
                        view.menuSugestoes();
                    } else {
                        handleEvent("VOLTAR");
                    }
                }    
        }
    }

    public void update() {}
}
