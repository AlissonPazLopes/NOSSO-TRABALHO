package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        if(cargaHoraria >= 0 && cargaHoraria <= 4) this.cargaHoraria = cargaHoraria;
    }

    public String getData() {return data;}
    public String getDescricao() {return descricao;}
    public int getCargaHoraria() {return cargaHoraria;}
}
