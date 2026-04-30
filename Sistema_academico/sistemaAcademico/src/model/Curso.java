package model;

public class Curso {
//Atributos
	private String curso;
	private String campus;
	private String periodo;
	private float nota;
	private String semestre;
	private int faltas;
	private String disciplina;
		
	//Construtores
	public Curso(String curso, String campus, String periodo, float nota, String semestre, int faltas,
			String disciplina) {
		super();
		this.curso = curso;
		this.campus = campus;
		this.periodo = periodo;
		this.nota = nota;
		this.semestre = semestre;
		this.faltas = faltas;
		this.disciplina = disciplina;
	}

	//Getters Setters
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

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
		
}
