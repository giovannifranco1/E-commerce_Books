package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.PessoaDAO;
import br.unitins.model.ebooks.Perfil;
import br.unitins.model.ebooks.Pessoa;
@Named
@ViewScoped
public class cadastroControlerAlterar implements Serializable{

	private static final long serialVersionUID = -5011159364992800173L;
	private Pessoa usuario;

	public cadastroControlerAlterar() {
	
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("detalheUsuario");
		setUsuario((Pessoa)flash.get("detalheUsuario"));
	}
		
	
	
	public void alterarUsuario() {
		PessoaDAO dao = new PessoaDAO();
		System.out.println(usuario.getNome());
		try {
			dao.alterar(getUsuario());
			Util.addInfoMessage("Alteração realizada com sucesso.");
			usuario = null;
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma alteração.");
			e.printStackTrace();
		}
		
		Util.redirect("listaCadastroUsuario.xhtml");
	}
	
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}
	
	public Pessoa getUsuario() {
		return usuario;
	}
	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}
}
