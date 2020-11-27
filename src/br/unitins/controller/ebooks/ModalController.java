package br.unitins.controller.ebooks;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.unitins.livro.dao.LivroDAO;
import br.unitins.model.ebooks.Livro;

@Named
@ViewScoped
public class ModalController extends Controller <Livro> implements Serializable {

	private static final long serialVersionUID = 3716849595246878738L;

	public ModalController() {
		super(new LivroDAO());
	}
	
	@Override
	public Livro getEntity() {
		if (entity == null)
			entity = new Livro();
		return entity;
	}
	
}
