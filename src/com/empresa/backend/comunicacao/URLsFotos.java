package com.empresa.backend.comunicacao;

import java.util.List;

import com.empresa.backend.entities.UrlsImages;

public class URLsFotos {

	private List<UrlsImages> urls;

	public URLsFotos(List<UrlsImages> urls) {
		super();
		this.urls = urls;
	}

	public List<UrlsImages> getUrls() {
		return urls;
	}

	public void setUrls(List<UrlsImages> urls) {
		this.urls = urls;
	}
		
}
