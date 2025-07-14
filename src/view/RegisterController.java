package view;

import model.Model;
import model.Aluno;
import model.Professor;

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
                    Aluno aluno = new Aluno(nome, login, senha, Integer.parseInt(matricula), curso);
                    model.setUsuario(aluno);
                } else if(tipoUsuario.equals("2")) {
                    Professor professor = new Professor(nome, login, senha, view.getDisciplinas());
                    model.setUsuario(professor);
                }
                model.detachObserver(this);
                break;
        }
    }

    public void update() {

    }
}
