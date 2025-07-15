package model;

public class Aluno extends Usuario {
    private int matricula;
    private String curso; // Decidir sobre esse atributo depois (a ideia era uma tela de sugestões de planos de ensino após login no sistema, com base no curso do aluno)

    public Aluno(String nome, String login, String senha, int matricula, String curso) {
        super(nome, login, senha);
        setMatricula(matricula);
        setCurso(curso);
    }

    public void setMatricula(int matricula) {
        if(matricula >= 100000 && matricula <= 999999) {
            this.matricula = matricula;
        }
    }

    public int getMatricula() {
        return matricula;
    }

    //melhorar setCurso caso o atributo seja mantido no projeto
    public void setCurso(String curso) {
        if(curso != null) {
            this.curso = curso;
        }
    }

    public String getCurso() {
        return curso;
    }

    public String toString() {
        String perfil = "[1] - Nome: " + getNome() +
                        "\n[2] - Login: " + getLogin() +
                        "\n[3] - Senha: " + getSenha() +
                        "\n[4] - Matricula: " + getMatricula() +
                        "\n[5] - Curso: " + getCurso();

        return perfil;
    }
}
