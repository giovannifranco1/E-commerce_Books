package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.model.ebooks.Livro;

@Named
@ViewScoped
public class DetalhesLivroController implements Serializable{

	private static final long serialVersionUID = 4434070164863604203L;
	private Livro livro;
	
	public DetalhesLivroController() {
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("detalheFlash");
		setLivro((Livro)flash.get("detalheFlash"));
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
	
	
	
}
