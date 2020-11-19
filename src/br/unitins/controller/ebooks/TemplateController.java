package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Session;
import br.unitins.application.ebooks.Util;
import br.unitins.model.ebooks.Pessoa;

@Named
@ViewScoped
public class TemplateController implements Serializable {

		
//	private Usuario usuarioLogado = null;

	private static final long serialVersionUID = -6431473045388205715L;

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("login.xhtml");
	}

	public Pessoa getUsuarioLogado() {
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null)
			return null;
		return (Pessoa) obj;
	}
	

}

