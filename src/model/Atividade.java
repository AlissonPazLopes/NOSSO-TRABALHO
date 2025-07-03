package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Atividade {
    private LocalDate data;
    private String descricao;
    private int cargaHoraria;

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public void setDescricao(String descricao) {this.descricao = descricao;}
    public String getDescricao() {return descricao;}

    public void setCargaHoraria(int cargaHoraria) {
        if(cargaHoraria >= 0 && cargaHoraria <= 4) this.cargaHoraria = cargaHoraria;
    }

    public int getCargaHoraria() {return cargaHoraria;}
}
