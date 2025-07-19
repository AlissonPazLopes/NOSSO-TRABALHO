package model;

import view.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    //dados
    private static Model instanciaUnica;
    private Usuario usuarioAutenticado;

    //private HashMap<String, Ped> peds = new HashMap<>();
    private ArrayList<Ped> peds = new ArrayList<>();
    private HashMap<String, Usuario> usuarios = new HashMap<>();
    private ArrayList<Observer> observers = new ArrayList<>();

    /*
     * implementacao do singleton
     */

    private Model() {}

    //permite a criacao de apenas uma instancia de model; retorna a unica instancia
    public static Model getInstancia() {
        if(instanciaUnica == null) instanciaUnica = new Model();
        return instanciaUnica;
    }

    /*
     * metodos envolvendo usuario
     */

    //adiciona um usuario qualquer
    public void setUsuario(Usuario usuario) {
        if (usuario != null && usuario.getLogin() != null) {
            usuarios.put(usuario.getLogin(), usuario);
            notifica();
        }
    }

    //cria e adiciona um aluno
    public void setUsuario(String nome, String login, String senha, int matricula, String curso){
        Aluno aluno = new Aluno(nome, login, senha, matricula, curso);
        setUsuario(aluno);
    }

    //retorna um aluno com base na matricula
    public Aluno getAlunoPorMatricula(String matricula) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario instanceof Aluno) {
                Aluno aluno = (Aluno) usuario;
                if (String.valueOf(aluno.getMatricula()).equals(matricula)) {
                    return aluno;
                }
            }
        }
        return null;
    }


    //cria e adiciona um professor
    public void setUsuario(String nome, String login, String senha, ArrayList<Disciplina> disciplinas){
        Professor professor = new Professor(nome, login, senha, disciplinas);
        setUsuario(professor);
    }

    public void atualizaUsuario(Usuario usuario) {
        usuarioAutenticado = usuario;
        notifica();
    }

    public void atualizaUsuario(Usuario usuario, String loginAntigo) {
        usuarioAutenticado = usuario;
        if (loginAntigo != null && !loginAntigo.equals(usuario.getLogin())) usuarios.remove(loginAntigo);
        usuarios.put(usuario.getLogin(), usuario);
        notifica();
    }

    public void removeUsuario(String login) {usuarios.remove(login);}

    public boolean autenticarUsuario(String login, String senha) {
        Usuario usuario;
        boolean autenticado = false;
        if(login != null && senha != null) {
            usuario = usuarios.get(login);
            if(usuario != null) {
                if(login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
                    usuarioAutenticado = usuario;
                    autenticado = true;
                }
            }
        }
        notifica();
        return autenticado;
    }

    public void deslogarUsuario() {
        usuarioAutenticado = null;
        notifica();
    }

    public Usuario getUsuarioAutenticado() {return usuarioAutenticado;}
    public int getTotalUsuarios() {return usuarios.size();}
    public Usuario getUsuario(String login) {return usuarios.get(login);} //retorna um usuario especifico

    /*
     *metodos envolvendo ped
     */

    //metodo que cria e retorna um calendario de atividades
    public ArrayList<Atividade> criarCalendario(ArrayList<String> datas, ArrayList<String> descricoes, ArrayList<Integer> horas) {
        ArrayList<Atividade> calendario = new ArrayList<>();

        for(int i = 0; i < datas.size(); i++) {
            Atividade atividade = new Atividade();

            String data = datas.get(i);
            String descricao = descricoes.get(i);
            int hora = horas.get(i);

            atividade.setData(data);
            atividade.setDescricao(descricao);
            atividade.setCargaHoraria(hora);

            calendario.add(atividade);
        }
        return calendario;
    }

    public void addPed(Ped ped) {
        if(ped != null) {
            peds.add(ped);
            notifica();
        }
    }

    public void cadastrarPed(
    String codigo,
    Professor professor,
    String curso,
    String nomeDisciplina,
    CaraterDisciplina carater,
    CargaHoraria cargaHoraria,
    String requisitos,
    String justificativa,
    String ementa,
    String objetivos,
    ArrayList<Atividade> calendario,
    String metodologia,
    String atividadesDiscentes,
    String sistemaAvaliacao,
    String bibliografia
) {
    Disciplina disciplina = new Disciplina(nomeDisciplina, codigo);
    Ped ped = new Ped(
        codigo,
        professor,
        curso,
        disciplina,
        carater,
        cargaHoraria,
        requisitos,
        justificativa,
        ementa,
        objetivos,
        calendario,
        metodologia,
        atividadesDiscentes,
        sistemaAvaliacao,
        bibliografia
    );
    addPed(ped);
}

    public void removePed(Ped ped) {
        peds.remove(ped);
        notifica();
    }

    public ArrayList<Ped> getPeds() {
        return peds;
    }

    /*
     *metodos envolvendo observers
     */

    public void notifica() {for(Observer o : observers) o.update();}
    public void attachObserver(Observer o) {observers.add(o);}
    public void detachObserver(Observer o) {observers.remove(o);}

}