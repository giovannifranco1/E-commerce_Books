package br.unitins.controller.ebooks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Session;
import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.VendaDAO;
import br.unitins.model.ebooks.Pessoa;
import br.unitins.model.ebooks.Venda;
@Named
@ViewScoped
public class HistoricoController implements Serializable{
	
	private static final long serialVersionUID = -7999044215496568337L;
	
	private List<Venda> listaVenda;
	private Double total;
	
	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			VendaDAO dao = new VendaDAO();
			Object obj = Session.getInstance().getAttribute("usuarioLogado");
			
			if (obj != null)
				try {
					listaVenda = dao.obterTodos((Pessoa) obj);
				} catch (Exception e) {
					Util.addErrorMessage("Não foi possível obter o histórico de vendas.");
					listaVenda = new ArrayList<Venda>();
				}
			
		}
		return listaVenda;
	}
	
	public void detalhes(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("detalhesHistorico", venda);
		Util.redirect("detalhesHistorico.xhtml");
		
	}
	
	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getTotal() {
		return total;
	}
	
}

	


