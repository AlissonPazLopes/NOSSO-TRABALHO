package view;

import java.util.*;
import model.*;

public class LoginView implements Observer {
    private Model model;
    private LoginController controller;
    private String login;
    private String senha;

    //inicializa a view com controller e o model
    public void init(Model model) {
        this.model = model;
        controller = new LoginController();
        controller.init(model, this);
        model.attachObserver(this);
        logarUsuario();
    }

    //Vai coletar as credenciais do usuário, enviando para o controller
    public void logarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("""
        ================================================
                           Login
        ================================================
        """);
        System.out.print("Digite seu login: ");
        login = sc.nextLine();
        System.out.print("Digite sua senha: ");
        senha = sc.nextLine();
        controller.handleEvent("OK");

        model.detachObserver(this);
    }

    //retorna o login
    public String getLogin() {
        return login;
    }

    //retorna a senha
    public String getSenha() {
        return senha;
    }

    //Dá um update no model
    public void update() {

    }

    public void exibirMsg(String msg) {
        System.out.println();
        System.out.println(msg);
        System.out.println();
    }
}
