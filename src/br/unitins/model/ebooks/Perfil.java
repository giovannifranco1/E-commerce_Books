package br.unitins.model.ebooks;

public enum Perfil {
	ADM(1, "administrador"),
	CLIENTE(2, "cliente");
	
	
	private int id;
	private String label;
	
	
	private Perfil(int id, String label) {
		this.id = id;
		this.label= label;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}
	
	public static Perfil valueOf(int id) {
		for (Perfil perfio : Perfil.values()) {
			if(id == perfio.getId()) {
				return perfio;
			}
		}
		return null;
	}
}
