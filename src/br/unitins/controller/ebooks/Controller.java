package br.unitins.controller.ebooks;

import java.util.ArrayList;
import java.util.List;

import br.unitins.application.ebooks.Util;
import br.unitins.livro.dao.DAO;
import br.unitins.livro.dao.PessoaDAO;
import br.unitins.model.ebooks.Pessoa;



public abstract class Controller<T> {
	
	protected T entity;
	private DAO<T> dao = null;
	// lista das entidades 
	private List<T> listaEntity;
	
	
	public Controller(DAO<T> dao) {
		super();
		this.dao = dao;
	}

	public void incluir() {
		
		try {
			dao.inserir(getEntity());
			Util.addInfoMessage("Inclusão realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
		}
	}
	
	public void alterar() {
		try {
			dao.alterar(getEntity());
			Util.addInfoMessage("Alteração realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma alteração.");
			e.printStackTrace();
		}
	}

	public void excluir() {
		excluir(getEntity());
	}

	public void excluir(T entity) {
		try {
			dao.excluir(entity);
			Util.addInfoMessage("Exclusão realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma exclusão.");
			e.printStackTrace();
		}
	}
	
	public void editar(T entity) {
		try {
			setEntity(dao.obterUm(entity));
		} catch (Exception e) {
			Util.addErrorMessage("Problema ao editar.");
			e.printStackTrace();
		}
	}
	
	public List<T> getListaEntity() {
		if (listaEntity == null) {
			try {
				listaEntity = dao.obterTodos();
			} catch (Exception e) {
				e.printStackTrace();
				listaEntity = new ArrayList<T>();
			}
		}	
		return listaEntity;
	}
	public void setListaEntity(List<T> listaEntity) {
		this.listaEntity = listaEntity;
	}
	
	public void limpar() {
		entity = null;
		listaEntity = null;
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public static boolean comfirmarEmaiil(Pessoa Usuario) {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		PessoaDAO dao = new PessoaDAO();
		try {
			lista = dao.obterTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Pessoa pessoa : lista) {
			if(Usuario.getEmail().equals(pessoa.getEmail())) {
				return true;
			}
		}
		return false;
	}
}
