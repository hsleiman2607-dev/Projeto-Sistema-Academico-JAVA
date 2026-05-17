package model;

public class AlunoCursoNota {

    // Classe usada como uma "caixinha" para carregar aluno, curso e nota juntos
    private Aluno aluno;
    private Curso curso;
    private Nota nota;

    // Construtor usado quando quero montar o objeto já com aluno, curso e nota
    public AlunoCursoNota(Aluno aluno, Curso curso, Nota nota) {
        this.aluno = aluno;
        this.curso = curso;
        this.nota = nota;
    }

    // Getters e setters: servem para acessar e alterar os objetos guardados aqui
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
}