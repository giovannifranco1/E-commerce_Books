package br.unitins.controller.ebooks;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.PessoaDAO;
import br.unitins.model.ebooks.Livro;
import br.unitins.model.ebooks.Perfil;
import br.unitins.model.ebooks.Pessoa;


@Named
@RequestScoped
public class LoginController extends Controller<Pessoa> {

	public LoginController() {
		super(new PessoaDAO());
	}

	public String validar() {
		for (Pessoa pessoa : getListaEntity()) {

			if (getEntity().getEmail().toLowerCase().equals(pessoa.getEmail().toLowerCase())
					&& getEntity().getSenha().equals(pessoa.getSenha())) {
				Util.redirect("cadastro.xhtml");
				return "";
			}
			Util.addErrorMessage("Login ou senha incorreto!");
		}
		return "";
	}
	

	@Override
	public Pessoa getEntity() {
		if (entity == null) {
			entity = new Pessoa();
		}
		return entity;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public String login() {
		Util.redirect("index.xhtml");
		return "";
	}

	public String cadastroUsuario() {
		Util.redirect("cadastroUsuario.xhtml");
		return "";
	}
}
