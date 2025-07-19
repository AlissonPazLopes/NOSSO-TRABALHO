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
        if(event == 0){
            view.encerrar();
            model.detachObserver(this);
        } else if(event == chaveAdd){
            view.exibirMsg("Escolha a posicao que deseja adicionar: ");
            int posicao = view.scannerInt();
            if(posicao < 1 || posicao > chaveAdd){
                view.exibirMsg("Digite uma posicao valida: ");
                view.editarCalendario();
            } else{
                view.exibirMsg("Data[DD/MM]: ");
                String data = view.scannerString();
                view.exibirMsg("Descricao: ");
                String descricao = view.scannerString();
                view.exibirMsg("Carga Horaria: ");
                int horas = view.scannerInt();

                Atividade atividade = new Atividade(data, descricao, horas);

                ArrayList<Atividade> calendario = ped.getCalendario();
                ArrayList<Atividade> pedsTemporariosCabeca = new ArrayList<>();
                ArrayList<Atividade> pedsTemporariosCauda = new ArrayList<>();
                //atividades antes do que vai ser adicionado
                for(int i = 0; i < posicao; i++){
                    pedsTemporariosCabeca.add(ped.getAtividade(i));
                }
                //atividades depois do que vai ser adicionado
                for(int i = posicao; i < calendario.size(); i++){
                    pedsTemporariosCauda.add(ped.getAtividade(i));
                }

                ArrayList<Atividade> calendarioFinal = new ArrayList<>();
                for(Atividade atv: pedsTemporariosCabeca) calendarioFinal.add(atv);
                calendarioFinal.add(atividade);
                for(Atividade atv: pedsTemporariosCauda) calendarioFinal.add(atv);


            }
        } else if(event > 0 && event < chaveAdd){

        } else{
            view.exibirMsg("Opção inválida.");
            view.editarCalendario();
        }
    }

    public void update() {}
}
