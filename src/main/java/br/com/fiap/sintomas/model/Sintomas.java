package br.com.fiap.sintomas.model;

public class Sintomas {
	
	private Integer id_sintoma;
	private String sintomas;
	private String especialidade;
	
	
	public Integer getId_sintoma() {
		return id_sintoma;
	}
	public void setId_sintoma(Integer id_sintoma) {
		this.id_sintoma = id_sintoma;
	}
	
	public String getSintomas() {
		return sintomas;
	}
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	
}
