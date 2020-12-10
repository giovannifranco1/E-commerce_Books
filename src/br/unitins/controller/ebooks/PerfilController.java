package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Session;
import br.unitins.application.ebooks.Util;
import br.unitins.model.ebooks.Pessoa;
@Named
@ViewScoped
public class PerfilController implements Serializable{

	private static final long serialVersionUID = -8381069191112190871L;
	private Pessoa usuario;
	public PerfilController() {
		setUsuario((Pessoa)Session.getInstance().getAttribute("usuarioLogado"));
	}
	public Pessoa getUsuario() {
		return usuario;
	}
	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}
	
	public void editarUsuario() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("detalheCliente", getUsuario());
		Util.redirect("cadastroUsuario.xhtml");
	}
}
