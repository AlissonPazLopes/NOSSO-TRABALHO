package model;

public enum CargaHoraria {
    HORAS_32(32),
    HORAS_64(64);

    private int horas;

    CargaHoraria(int horas) {
        this.horas = horas;
    }

    public int getHoras() {
        return horas;
    }
}
