package model;

import view.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    //dados
    private static Model instanciaUnica;
    private Usuario usuarioAutenticado;

    private HashMap<String, Ped> peds = new HashMap<>();
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

    public void addUsuario(Usuario usuario) {
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

    public void addPed(Ped ped) {
        if(ped != null && ped.getCodigo() != null) {
            peds.put(ped.getCodigo(), ped);
            notifica();
        }
    }

    public void removePed(String codigo) {
        if(peds.get(codigo) != null) {peds.remove(codigo);}
    }

    public Ped getPed(String codigo) {return peds.get(codigo);} //retorna um ped especifico
    public int getTotalPeds() {return peds.size();}

    /*
     *metodos envolvendo observers
     */

    public void notifica() {for(Observer o : observers) o.update();}
    public void addObserver(Observer o) {observers.add(o);}
    public void removeObserver(Observer o) {observers.remove(o);}

}
