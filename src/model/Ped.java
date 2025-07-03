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

    public String getCodigo() {
        return codigo;
    }

    public void setJustificativa(String justificativa) {
        if(justificativa != null) {
            this.justificativa = justificativa;
        }
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setEmenta(String ementa) {
        if(ementa != null) {
            this.ementa = ementa;
        }
    }

    public String getEmenta() {
        return ementa;
    }

    public void setObjetivos(String objetivos) {
        if(objetivos != null) {
            this.objetivos = objetivos;
        }
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setMetodologia(String metodologia) {
        if(metodologia != null) {
            this.metodologia = metodologia;
        }
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setAtividadesDiscentes(String atividadesDiscentes) {
        if(atividadesDiscentes != null) {
            this.atividadesDiscentes = atividadesDiscentes;
        }
    }

    public String getAtividadesDiscentes() {
        return atividadesDiscentes;
    }

    public void setSistemaAvaliacao(String sistemaAvaliacao) {
        if(sistemaAvaliacao != null) {
            this.sistemaAvaliacao = sistemaAvaliacao;
        }
    }

    public String getSistemaAvaliacao() {
        return sistemaAvaliacao;
    }

    public void setBibliografia(String bibliografia) {
        if(bibliografia != null) {
            this.bibliografia = bibliografia;
        }
    }

    public String getBibliografia() {
        return bibliografia;
    }
}
