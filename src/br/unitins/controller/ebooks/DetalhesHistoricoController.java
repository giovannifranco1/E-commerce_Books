package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.model.ebooks.Venda;

@Named
@ViewScoped
public class DetalhesHistoricoController implements Serializable{

	private static final long serialVersionUID = 3191062969020877896L;
	
	private Venda venda;
	
	public DetalhesHistoricoController() {
		super();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("detalhesHistorico");
		setVenda((Venda)flash.get("detalhesHistorico"));
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Venda getVenda() {
		return venda;
	}
	
}
