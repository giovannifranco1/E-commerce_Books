package br.unitins.controller.ebooks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.LivroDAO;
import br.unitins.model.ebooks.Livro;

@Named
@ViewScoped
public class ListaController extends Controller <Livro> implements Serializable  {

	public ListaController() {
		super(new LivroDAO());	
	}

	private static final long serialVersionUID = -1887567031177524999L;
	
	private String tituloBusca;

	private Livro livro;
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Livro getLivro() {
		return livro;
	}
	public void detalhesLivro(Livro livro) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("detalheLivro", livro);
		Util.redirect("cadastro.xhtml");
	}
	
	public String getTituloBusca() {
		return tituloBusca;
	}

	public void setTituloBusca(String tituloBusca) {
		this.tituloBusca = tituloBusca;
	}
	

	public void atualizar() {
		setListaEntity(null);
		setListaEntity(filtrandoLista(getListaEntity()));
	}
	@Override
	public Livro getEntity() {
		if(entity == null)
			entity = new Livro();
		
		return entity;
	}
	public List<Livro> filtrandoLista(List<Livro> lista) {
		List<Livro> listaFiltrada = new ArrayList<Livro>();
		for (Livro livro : lista) {
			if (livro.getTitulo().toLowerCase().contains(getTituloBusca().toLowerCase().trim()))
				listaFiltrada.add(livro);
		}
		return listaFiltrada;
	}
}
