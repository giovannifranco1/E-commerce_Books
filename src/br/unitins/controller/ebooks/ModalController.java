package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.unitins.livro.dao.LivroDAO;
import br.unitins.model.ebooks.Livro;

@Named
@ViewScoped
public class ModalController extends Controller <Livro> implements Serializable {

	private static final long serialVersionUID = 3716849595246878738L;

	private Livro livro;
	private String teste= "teste";
	
	public String getTeste() {
		return teste;
	}
	public void setTeste(String teste) {
		this.teste = teste;
	}
	public ModalController() {
		super(new LivroDAO());
			if(getEntity() == null) 
				return ;
			Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
			flash.keep("detalheLivro");
			setLivro((Livro)flash.get("detalheLivro"));
			System.out.println(getLivro().getNumeroPaginas());
			System.out.println(entity);
	}
		
	@Override
	public Livro getEntity() {
		if (entity == null)
			entity = new Livro();

		return entity;
	}
	
	public Livro getLivro() {
		if(livro == null) {
			livro = new Livro();
			
		}
		return livro;
	}

	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public void add() {
		this.teste = "ola";
	}
	
}
