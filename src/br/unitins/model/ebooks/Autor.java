package br.unitins.model.ebooks;

import java.time.LocalDate;

public class Autor {
	private Integer id;
	private String nome;
	private LocalDate dataNascimento;
	private String biografia;
	
	
	
	
	
	
	
	
	
	
	

	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	
	
	
}
