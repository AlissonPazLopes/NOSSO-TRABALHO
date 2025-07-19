package view;

import model.Model;
import model.Ped;

public class EditarPlanoController implements Observer {
    private Model model;
    private EditarPlanoView view;
    private Ped ped;

    public void init(Model model, EditarPlanoView view, Ped ped) {
        this.model = model;
        this.view = view;
        this.ped = ped;
        model.attachObserver(this);
    }

    public void handleEvent(String event) {
        if("SALVAR".equals(event)) {
            ped.setCarater(view.getCarater());
            ped.setCargaHoraria(view.getCargaHoraria());
            ped.setRequisitos(view.getRequisitos());
            ped.setJustificativa(view.getJustificativa());
            ped.setEmenta(view.getEmenta());
            ped.setObjetivos(view.getObjetivos());
            ped.setMetodologia(view.getMetodologia());
            ped.setAtividadesDiscentes(view.getAtividadesDiscentes());
            ped.setSistemaAvaliacao(view.getSistemaAvaliacao());
            ped.setBibliografia(view.getBibliografia());
            view.exibirMsg("Plano atualizado com sucesso!");
            voltarParaPlanosCriados();
        }
    }

    private void voltarParaPlanosCriados() {
        model.detachObserver(this);
        PlanosCriadosView viewPC = new PlanosCriadosView();
        viewPC.init(model);
    }

    public void update() {}
}