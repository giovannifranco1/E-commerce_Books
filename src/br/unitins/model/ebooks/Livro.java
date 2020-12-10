package br.unitins.model.ebooks;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class Livro {
	private Integer id ;
	@NotBlank(message = "O Titulo não pode ser nulo")
	private String titulo;
	@NotBlank(message = "O editora não pode ser nulo")
	private String editora;
	@NotBlank(message = "A idioma não pode ser nulo")
	private String idioma;
	@NotBlank(message = "O descricao não pode ser nulo")
	private String descricao;
	@NotBlank(message = "A categoria não pode ser nulo")
	private String categoria;
	private LocalDate ano;	

	@Size(min = 1, max = 10, message = "O numero de paginas deve conter no maximo 10 digitos")
	private String numeroPaginas;
	
	private Double preco;
	private Autor autor;
	
	
	public Livro() {
		
	}
	public Livro(Integer id) {
		super();
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public LocalDate getAno() {
		return ano;
	}
	public void setAno(LocalDate ano) {
		this.ano = ano;
	}
	public String getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(String numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Double getPreco() {
		return preco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	
	
}
