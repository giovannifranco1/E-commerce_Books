package br.unitins.controller.ebooks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.PessoaDAO;
import br.unitins.model.ebooks.Pessoa;
@Named
@ViewScoped
public class ListaControllerUsuario extends Controller<Pessoa> implements Serializable{

	private static final long serialVersionUID = 6325584520794541141L;
	
	public ListaControllerUsuario() {
		super(new PessoaDAO());
	}

	private String tituloBusca;
	
	@Override
	public Pessoa getEntity() {
		if(entity == null)
			entity = new Pessoa();
		return null;
	}
	public void atualizar() {
		setListaEntity(null);
		setListaEntity(filtrandoLista(getListaEntity()));
	}
	public List<Pessoa> filtrandoLista(List<Pessoa> lista) {
		List<Pessoa> listaFiltrada = new ArrayList<Pessoa>();
		for (Pessoa usuario : lista) {
			if (usuario.getNome().toLowerCase().contains(getTituloBusca().toLowerCase().trim()))
				listaFiltrada.add(usuario);
		}
		return listaFiltrada;
	}
	public void detalhesUsuario(Pessoa Usuario) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("detalheUsuario", Usuario);
		Util.redirect("cadastroAdministrador.xhtml");
	}
	public String getTituloBusca() {
		return tituloBusca;
	}
	public void setTituloBusca(String tituloBusca) {
		this.tituloBusca = tituloBusca;
	}
	
	
}
