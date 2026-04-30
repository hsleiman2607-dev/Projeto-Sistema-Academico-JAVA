package model;

public class Aluno {
	//Atributos
	private String rgm;
	private String cpf;
	private String nome;
	private String data_nasc;
	private String email;
	private String numero;
	private String endereco;
	private String complemento;
	private String municipio;
	private String uf;
	
	//Construtores
	public Aluno() {
	}
	public Aluno(String rgm, String cpf, String nome, String data_nasc, String email, String numero, String endereco,
			String complemento, String municipio, String uf) {
		super();
		this.rgm = rgm;
		this.cpf = cpf;
		this.nome = nome;
		this.data_nasc = data_nasc;
		this.email = email;
		this.numero = numero;
		this.endereco = endereco;
		this.complemento = complemento;
		this.municipio = municipio;
		this.uf = uf;
	}
	//Getters Setters
	public String getRgm() {
		return rgm;
	}
	public void setRgm(String rgm) {
		this.rgm = rgm;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
