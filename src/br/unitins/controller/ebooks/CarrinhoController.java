package br.unitins.controller.ebooks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;



import br.unitins.application.ebooks.Session;
import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.VendaDAO;
import br.unitins.model.ebooks.ItemVenda;
import br.unitins.model.ebooks.Pessoa;
import br.unitins.model.ebooks.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = -8209814887157105292L;
	private Venda venda;
	private Double total;
	@SuppressWarnings("unchecked")
	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
			venda.setListaItemVenda(new ArrayList<ItemVenda>());
		}
		// obtendo o carrinho da sessao
		Object obj = Session.getInstance().getAttribute("carrinho");
		if (obj != null)
			venda.setListaItemVenda((List<ItemVenda>) obj);
	
		return venda;
	}
	
	public void remover(ItemVenda itemVenda) {
		venda.getListaItemVenda().remove(itemVenda);
	}
	public void total() {
		for (ItemVenda venda : venda.getListaItemVenda()) {
			this.total += venda.getPreco();
		}
	}
	
	public void finalizar() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null) {
			Util.addErrorMessage("Para finalizar a venda o usuário deve estar logado.");
			return;
		}
		
		// adicionando o usuario logado na venda
		getVenda().setUsuario((Pessoa) obj);
		
		VendaDAO dao = new VendaDAO();
		try {
			dao.inserir(getVenda());
			Util.addInfoMessage("Inclusão realizada com sucesso.");
			
			// limpando o carrinho
			Session.getInstance().setAttribute("carrinho", null);
			setVenda(null);
			
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
		}
		
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getTotal() {
		total = 0.0;
		total();
		return total;
	}

}

