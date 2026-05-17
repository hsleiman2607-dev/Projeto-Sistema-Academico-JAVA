package model;

public class Nota {
    
    // Dados da nota cadastrada para uma disciplina do aluno
    private int idNota;
    private String disciplina;
    private String semestre;
    private String nota;
    private int faltas;
    private String rgm;
    
    // Construtor vazio usado quando quero criar a nota e preencher depois com setters
    public Nota() {
    }

    // Construtor completo, usado quando já tenho os dados da nota de uma vez
    public Nota(String disciplina, String semestre, String nota, int faltas, String rgm) {
        this.disciplina = disciplina;
        this.semestre = semestre;
        this.nota = nota;
        this.faltas = faltas;
        this.rgm = rgm;
    }

    // Getters e setters: servem para acessar e alterar os atributos privados da classe
    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public String getRgm() {
        return rgm;
    }

    public void setRgm(String rgm) {
        this.rgm = rgm;
    }
}