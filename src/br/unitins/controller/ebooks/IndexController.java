package br.unitins.controller.ebooks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Session;
import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.LivroDAO;
import br.unitins.model.ebooks.ItemVenda;
import br.unitins.model.ebooks.Livro;

@Named
@ViewScoped
public class IndexController extends Controller <Livro> implements Serializable  {

	private static final long serialVersionUID = -35677165536221993L;

	public IndexController() {
		super(new LivroDAO());
	}
	
	private Integer tipoFiltro;
	private String filtro;
	private List<Livro> listaLivro;
	
	public void buscar() {
		LivroDAO dao = new LivroDAO();
		try {
			setListaLivro(dao.obterListaMidia(tipoFiltro, filtro));
		} catch (Exception e) {
			e.printStackTrace();
			setListaLivro(null);
		}
	}
	@SuppressWarnings("unchecked")
	public void addCarrinho(Livro livro) {
			try {
				LivroDAO dao = new LivroDAO();
		
				livro = dao.obterUm(livro);
				
				List<ItemVenda> listaItemVenda = null;
				Object obj = Session.getInstance().getAttribute("carrinho");
				
				if (obj == null) 
					listaItemVenda = new ArrayList<ItemVenda>();
				else 
					listaItemVenda = (List<ItemVenda>) obj;
				
				// montando o item de venda
				ItemVenda item = new ItemVenda();
				item.setLivro(livro);
				item.setPreco(livro.getPreco());
				listaItemVenda.add(item);
				
				// atualizando a sessao do carrinho de compras
				Session.getInstance().setAttribute("carrinho", listaItemVenda);
				
				Util.addInfoMessage("O produto: " + livro.getTitulo() + " foi adicionado ao carrinho.");
				
			} catch (Exception e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao adicionar o produto ao carrinho. Tente novamente.");
			}
		
	}


	@Override
	public Livro getEntity() {
		if(entity == null)
			entity = new Livro();
		return entity;
	}

	
	
	
	
	
	
	
	public Integer getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Livro> getListaLivro() {
		return listaLivro;
	}
	public void setListaLivro(List<Livro> listaLivro) {
		this.listaLivro = listaLivro;
	}
}