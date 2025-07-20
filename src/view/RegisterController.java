package view;

import model.Model;

public class RegisterController implements Observer {
    private Model model;
    private RegisterView view;

    public void init(Model model, RegisterView view) {
        if(model != null && view != null) {
            this.model = model;
            this.view = view;
            model.attachObserver(this);
        }
    }

    public void handleEvent(String event) {
        switch(event) {
            case "OK":
                String nome = view.getNome();
                String login = view.getLogin();
                String senha = view.getSenha();
                String tipoUsuario = view.getTipoUsuario();

                if(tipoUsuario.equals("1")) {
                    String curso = view.getCurso();
                    String matricula = view.getMatricula();
                    model.setUsuario(nome, login, senha, Integer.parseInt(matricula), curso);
                } else if(tipoUsuario.equals("2")) {
                    model.setUsuario(nome, login, senha, view.getDisciplinas());
                }
                model.detachObserver(this);
                break;
        }
    }


    public void update() {
// não sei fazer o que tem aqui ainda!
    }
}
