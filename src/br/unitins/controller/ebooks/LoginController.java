package br.unitins.controller.ebooks;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Session;
import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.DAO;
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
	PessoaDAO dao = new PessoaDAO();
	public void logar() {
		try {
			Pessoa usuarioLogado =

					dao.obterUsuario(getEntity().getEmail(),
					Util.hash(charEmail(getEntity().getEmail().toCharArray())
							+ getEntity().getSenha()));
			if (usuarioLogado == null)
				Util.addErrorMessage("Usuário ou senha inválido.");
			else {
				Session.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				Util.redirect("cadastro.xhtml");
			}	

		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao verificar o Login. Entre em contato pelo email: contato@email.com.br");
		}
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
		Util.redirect("login.xhtml");
		return "";
	}

	public String cadastroUsuario() {
		Util.redirect("cadastroUsuario.xhtml");
		return "";
	}
	
	public static String charEmail(char[] array) {
		String emailNovo = "";
		for (char c : array) {
			if(c == '@')
				return emailNovo;
			emailNovo += c;
		}
		return null;
	}
}
