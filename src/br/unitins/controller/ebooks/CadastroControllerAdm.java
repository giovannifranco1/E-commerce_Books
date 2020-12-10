package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.PessoaDAO;
import br.unitins.model.ebooks.Pessoa;
@Named
@ViewScoped
public class CadastroControllerAdm extends Controller<Pessoa> implements Serializable {

	private static final long serialVersionUID = 1626236131736285266L;

	public CadastroControllerAdm() {
		super(new PessoaDAO());		
	}
	private String confirmar;
	
	public void cadastrar() {
		PessoaDAO dao = new PessoaDAO();
		System.out.println(getConfirmar() +"   "+ getEntity().getSenha() +" "+ getEntity().getId());
		if(!(getConfirmar().equals(getEntity().getSenha()))) {
			Util.addErrorMessage("A senha não se corresponde!");
			return;
		}
		try {
			dao.inserir(getEntity());
			Util.addInfoMessage("Usuario cadastrado com sucesso!");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("Erro ao cadastrar usuario!");
			e.printStackTrace();
		}
	}
	@Override
	public Pessoa getEntity() {
		if (entity == null) 
			entity = new Pessoa();
		
		return entity;
	}
	
	public String getConfirmar() {
		return confirmar;
	}
	public void setConfirmar(String confirmar) {
		this.confirmar = confirmar;
	}
}
