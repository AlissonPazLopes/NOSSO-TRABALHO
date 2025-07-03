package model;

public class Ped {
    private String codigo; //private Identificacao identificacao ??? (para armazenar unidade/cursos/codigo/nome/carater etc etc...)
    private String justificativa;
    private String ementa;
    private String objetivos;
    //private Calendario calendario ???
    private String metodologia;
    private String atividadesDiscentes;
    private String sistemaAvaliacao;
    private String bibliografia;


    public void setCodigo(String codigo) {
        if(codigo == null) return;
        Ped ped = Model.getInstancia().getPed(codigo);
        if(ped != null) return;
        this.codigo = codigo;
    }
    public String getCodigo() {return codigo;}


}
