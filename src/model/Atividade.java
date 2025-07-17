package model;

public class Atividade {
    private String data;
    private String descricao;
    private int cargaHoraria;

    public Atividade() {}

    public Atividade(String data, String descricao, int cargaHoraria) {
        setData(data);
        setDescricao(descricao);
        setCargaHoraria(cargaHoraria);
    }


    public void setData(String data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getData() {return data;}
    public String getDescricao() {return descricao;}
    public int getCargaHoraria() {return cargaHoraria;}
}
