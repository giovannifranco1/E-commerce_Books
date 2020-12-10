package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.PessoaDAO;
import br.unitins.model.ebooks.Pessoa;

@Named
@ViewScoped
public class CadastroControllerSenha extends Controller<Pessoa> implements Serializable{

	private static final long serialVersionUID = 8651838947290578233L;
	Pessoa voltar = new Pessoa();
	public CadastroControllerSenha() {
		super(new PessoaDAO());
		if(getEntity() == null) 
			return ;
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("detalheUsuario");
		setEntity((Pessoa)flash.get("detalheUsuario"));
	}

	private String Comfirmar;
	
	public void alterarSenha() {
		PessoaDAO dao = new PessoaDAO();
		System.out.println(getComfirmar() +"   "+ getEntity().getSenha() +" "+ getEntity().getId());
		if(!(getComfirmar().equals(getEntity().getSenha()))) {
			Util.addErrorMessage("A senha não se corresponde");
			return;
		}
		try {
			dao.alterarSenha(getEntity());
			Util.addInfoMessage("senha alterada com sucesso!");
		} catch (Exception e) {
			Util.addErrorMessage("Erro ao alterar a senha!");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Pessoa getEntity() {
		if(entity == null)
			entity = new Pessoa();
		return entity;
	}
	
	public String getComfirmar() {
		return Comfirmar;
	}
	
	public void setComfirmar(String comfirmar) {
		Comfirmar = comfirmar;
	}
	
	public void voltar() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("detalheUsuario", getEntity());
		Util.redirect("cadastroAdministrador.xhtml");
	}
}
