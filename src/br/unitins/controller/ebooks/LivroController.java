package br.unitins.controller.ebooks;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livro.dao.LivroDAO;
import br.unitins.model.ebooks.Livro;

@Named
@ViewScoped
public class LivroController extends Controller <Livro> implements Serializable {
	
	private static final long serialVersionUID = 5331372062724563937L;

	public LivroController() {
		super(new LivroDAO());
	}
	
	@Override
	public Livro getEntity() {
		if (entity == null)
			entity = new Livro();
		return entity;

	}
	
	
}
