package model;

import java.util.ArrayList;

public class Ped {
    private String codigo;
    private Professor professor;
    private Disciplina disciplina;
    private CaraterDisciplina carater;
    private CargaHoraria cargaHoraria;
    private String requisitos;
    private String justificativa;
    private String ementa;
    private String objetivos;
    private ArrayList<Atividade> calendario = new ArrayList<>();
    private String metodologia;
    private String atividadesDiscentes;
    private String sistemaAvaliacao;
    private String bibliografia;

    public Ped(
        String codigo,
        Professor professor,
        Disciplina disciplina, 
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
            super();
            setCodigo(codigo);
            setProfessor(professor);
            setDisciplina(disciplina);
            setCarater(carater);
            setCargaHoraria(cargaHoraria);
            setRequisitos(requisitos);
            setJustificativa(justificativa);
            setEmenta(ementa);
            setObjetivos(objetivos);
            setCalendario(calendario);
            setMetodologia(metodologia);
            setAtividadesDiscentes(atividadesDiscentes);
            setSistemaAvaliacao(sistemaAvaliacao);
            setBibliografia(bibliografia);
    }

    public void setCodigo(String codigo) {
        Model model = Model.getInstancia();
        for(Ped ped: model.getPeds()) {
            if(ped.getCodigo().equals(codigo)) {return;}
        }
        this.codigo = codigo;
    }

    public String getCodigo() {return codigo;}

    public void setProfessor(Professor professor) {
        if(professor != null) this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setDisciplina(Disciplina disciplina) {
        if(disciplina.getNome() != null && disciplina.getCodigo() != null) this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {return disciplina;}

    public void setCarater(CaraterDisciplina carater) {
        if(carater != null) {
            this.carater = carater;
        }
    }

    public CaraterDisciplina getCarater() {
        return carater;
    }

    public void setCargaHoraria(CargaHoraria cargaHoraria) {
        if(cargaHoraria != null) {
            this.cargaHoraria = cargaHoraria;
        }
    }

    public CargaHoraria getCargaHoraria() {
        return cargaHoraria;
    }

    public void setRequisitos(String requisitos) {
        if(requisitos == null) {
            this.requisitos = "";
        }
        this.requisitos = requisitos;
    }

    public String getRequisitos() {
        return requisitos;
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

    public void setCalendario(ArrayList<Atividade> calendario){
        if(calendario != null) {
            this.calendario = calendario;
        }
    }

    public ArrayList<Atividade> getCalendario() {return calendario;}

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