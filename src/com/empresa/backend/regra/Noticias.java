package com.empresa.backend.regra;

import java.util.List;

public class Noticias {

	private List<Noticia> noticias;

	public Noticias(List<Noticia> noticias) {
		super();
		this.noticias = noticias;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
	
	
}
