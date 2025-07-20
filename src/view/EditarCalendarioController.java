package view;

import model.*;

import java.util.ArrayList;

public class EditarCalendarioController implements Observer{
    private Model model;
    private EditarCalendarioView view;
    private Ped ped;

    public void init(Model model, EditarCalendarioView view, Ped ped) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
            this.ped = ped;
            model.attachObserver(this);
        }
    }

    public void handleEvent(int event, int chaveAdd) {
        //sair
        if(event == 0){
            int horasCriadas = 0;
            for(Atividade atividade: ped.getCalendario()) horasCriadas += atividade.getCargaHoraria();
            if(horasCriadas != ped.getCargaHoraria().getHoras()){
                view.exibirMsg("\nA quantidade de horas criadas não corresponde à carga horária da disciplina.\n");
                view.editarCalendario();
            } else {
                view.encerrar();
                model.detachObserver(this);
            }
        //adicionar
        } else if(event == chaveAdd){
            view.exibirMsg("Escolha a posicao que deseja adicionar: ");
            int posicao = view.scannerInt();
            //posicao invalida
            if(posicao < 1 || posicao > chaveAdd){
                view.exibirMsg("Digite uma posicao valida.");
                view.editarCalendario();
            //posicao valida
            } else{
                view.exibirMsg("Data[DD/MM]: ");
                String data = view.scannerString();
                view.exibirMsg("Descricao: ");
                String descricao = view.scannerString();
                view.exibirMsg("Carga Horaria: ");
                int horas = view.scannerInt();

                Atividade atividadeNova = new Atividade(data, descricao, horas);

                // monta calendário final
                ArrayList<Atividade> oldCal = ped.getCalendario();
                ArrayList<Atividade> novoCal = new ArrayList<>();

                int idxInsercao = posicao - 1;
                for (int i = 0; i < idxInsercao; i++) {
                    novoCal.add(oldCal.get(i));
                }
                novoCal.add(atividadeNova);
                for (int i = idxInsercao; i < oldCal.size(); i++) {
                    novoCal.add(oldCal.get(i));
                }

                ped.setCalendario(novoCal);
                model.atualizaPed(ped);
                view.editarCalendario();


                ped.setCalendario(novoCal);
                model.atualizaPed(ped);
                view.editarCalendario();
            }
        //remover
        } else if (event >= 1 && event < chaveAdd) {
            int idxRemover = event - 1;
            ArrayList<Atividade> cal = ped.getCalendario();
            if (idxRemover >= 0 && idxRemover < cal.size()) {
                cal.remove(idxRemover);
                ped.setCalendario(cal);
                model.atualizaPed(ped);
            } else {
                view.exibirMsg("Índice fora do intervalo.\n");
            }
            view.editarCalendario();

            // inválido
        } else {
            view.exibirMsg("Opção inválida.\n");
            view.editarCalendario();
        }
    }

    public void update() {}
}
