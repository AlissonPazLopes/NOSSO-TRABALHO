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
    private HashMap<String, Ped> pedsSolicitados = new HashMap<>();
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

    /*public void addUsuario(Usuario usuario) {
        if (usuario != null && usuario.getLogin() != null) {
            usuarios.put(usuario.getLogin(), usuario);
            notifica();
        }
    }*/

    public void setUsuario(Usuario usuario) {
        if (usuario != null && usuario.getLogin() != null) {
            usuarios.put(usuario.getLogin(), usuario);
            notifica();
        }
    }

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

    public void addSolicitacaoPed(Ped ped) {
        Professor professor = (Professor) usuarioAutenticado; //so pode ser usado por professores
        if(professor.getDisciplina(ped.getDisciplina().getCodigo()) == null) return; //encerra se o professor e ped nao forem da mesma disciplina
        pedsSolicitados.put(ped.getCodigo(), ped);
        notifica();
    }

    /*public void addPed(String codigo) {
        if(pedsSolicitados.containsKey(codigo)) {
            peds.put(codigo, pedsSolicitados.get(codigo));
            notifica();
        }
    }*/

    public void addPed(Ped ped) {
        if(ped != null) {
            peds.add(ped);
            notifica();
        }
    }

    /*public void removePed(String codigo) {
        if(peds.get(codigo) != null) {peds.remove(codigo);}
    }*/

    public void removePed(Ped ped) {
        peds.remove(ped);
    }

    public ArrayList<Ped> getPeds() {
        return peds;
    }

    //public Ped getPed(String codigo) {return peds.get(codigo);} //retorna um ped especifico
    //public int getTotalPeds() {return peds.size();}

    /*
     *metodos envolvendo observers
     */

    public void notifica() {for(Observer o : observers) o.update();}
    public void attachObserver(Observer o) {observers.add(o);}
    public void detachObserver(Observer o) {observers.remove(o);}

}