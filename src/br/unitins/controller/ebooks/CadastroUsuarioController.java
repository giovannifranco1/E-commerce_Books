package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Session;
import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.PessoaDAO;
import br.unitins.model.ebooks.Perfil;
import br.unitins.model.ebooks.Pessoa;

@Named
@ViewScoped
public class CadastroUsuarioController extends Controller <Pessoa> implements Serializable{

	private static final long serialVersionUID = -5169761588040977198L;
	
	public CadastroUsuarioController() {
		super(new PessoaDAO());
		Pessoa usuario = (Pessoa)Session.getInstance().getAttribute("usuarioLogado");
		if(!(usuario == null))
		setEntity(usuario);
	}
		
	private String Confirmar;
	
	public void cadastrar() {
		PessoaDAO dao = new PessoaDAO();
		System.out.println(getConfirmar() +"   "+ getEntity().getSenha() +" "+ getEntity().getId());
		if(!(getConfirmar().equals(getEntity().getSenha()))) {
			Util.addErrorMessage("A senha não se corresponde");
			return;
		}
		try {
			dao.inserir(getEntity());
			Util.addInfoMessage("senha alterada com sucesso!");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("Erro ao alterar a senha!");
			e.printStackTrace();
		}
	}
	
	@Override
	public Pessoa getEntity() {
		if (entity == null) {
			entity = new Pessoa();
			entity.setPerfil(Perfil.CLIENTE);
		}
		return entity;
	}

	 public String getConfirmar() {
		return Confirmar;
	}
	 
	public void setConfirmar(String confirmar) {
		Confirmar = confirmar;
	}
}
