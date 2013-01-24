package com.empresa.backend.regra;

public class Noticia {
	
	private String texto;
	private String urlTiny;
	
	public Noticia(String texto, String urlTiny) {
		
		super();
		this.texto = texto;
		this.urlTiny = urlTiny;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUrlTiny() {
		return urlTiny;
	}

	public void setUrlTiny(String urlTiny) {
		this.urlTiny = urlTiny;
	}

}
