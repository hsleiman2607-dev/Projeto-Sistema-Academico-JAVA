package model;

public class Curso {
    
    // Dados principais do curso do aluno
    private String curso;
    private String campus;
    private String periodo;
    
    // Construtor vazio usado quando quero criar o curso e preencher depois com setters
    public Curso() {
    }

    // Construtor completo, usado quando já tenho todos os dados do curso de uma vez
    public Curso(String curso, String campus, String periodo) {
        this.curso = curso;
        this.campus = campus;
        this.periodo = periodo;
    }

    // Getters e setters: servem para acessar e alterar os atributos privados da classe
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}