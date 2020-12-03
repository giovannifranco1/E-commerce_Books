package br.unitins.model.ebooks;

import java.time.LocalDateTime;
import java.util.List;

public class Venda {
	private Integer id;
	private LocalDateTime data;
	private Pessoa usuario;
	private List<ItemVenda> listaItemVenda;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Pessoa getUsuario() {
		return usuario;
	}
	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}
	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}
	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
}
