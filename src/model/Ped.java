package model;

public class Ped {
    private String codigo;
    //adicionar restante


    public void setCodigo(String codigo) {
        if(codigo == null) return;
        Ped ped = Model.getInstancia().getPed(codigo);
        if(ped != null) return;
        this.codigo = codigo;
    }
    public String getCodigo() {return codigo;}


}
