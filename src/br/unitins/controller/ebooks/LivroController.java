package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.LivroDAO;
import br.unitins.model.ebooks.Livro;

@Named
@ViewScoped
public class LivroController extends Controller <Livro> implements Serializable {
	
	private static final long serialVersionUID = 5331372062724563937L;
	
	

	public LivroController() {
		super(new LivroDAO());
		if(getEntity() == null) 
			return ;
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("detalheLivro");
		setEntity((Livro)flash.get("detalheLivro"));
	}
	
	@Override
	public Livro getEntity() {
		if (entity == null)
			entity = new Livro();
		
		return entity;

	}
	
	public void message() {
		Integer message = 1;
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("message", message);
	}
	public void alterarCadastro() {
		LivroDAO dao = new LivroDAO();
		try {
			message();
			dao.alterar(getEntity());
			Util.addInfoMessage("Alteração realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma alteração.");
			e.printStackTrace();
		}
		Util.redirect("tabela.xhtml");
	}

}
