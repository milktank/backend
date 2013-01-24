package com.empresa.backend.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name="mobway_url_images")
@FilterDef(name="porEmpresa", parameters=@ParamDef( name="empresa", type="long" ))
@Filters( {
    @Filter(name="porEmpresa", condition="fk_empresa = :empresa")
} )
public class UrlsImages {

	@Id
	@GeneratedValue
	@Column(name="id_url")
	Long idurl;
	
	@Column(name="data_cadastro")
	Date dataUrl;
	
	@Column(name="url")
	String url;

	@Column(name="url_thumb")
	String urlThumb;
	
	@Column(name="legenda")
	String legenda;
	
	@ManyToOne
	@JoinColumn(name="fk_empresa")
	Empresas empresa;
	
	public UrlsImages() {
		super();
	}

	public UrlsImages(Long idurl, Date dataUrl, String url, String urlThumb, String legenda) {
		super();
		this.idurl = idurl;
		this.dataUrl = dataUrl;
		this.url = url;
		this.urlThumb = urlThumb;
		this.legenda = legenda;
	}
	
	public UrlsImages(Long idurl, Date dataUrl, String url) {
		super();
		this.idurl = idurl;
		this.dataUrl = dataUrl;
		this.url = url;
	}
	
	public UrlsImages(Date dataUrl) {
		super();
		this.dataUrl = dataUrl;
	}
	
	public UrlsImages(String url, String urlThumb) {
		super();
		this.dataUrl = Calendar.getInstance().getTime();
		this.url = url;
		this.urlThumb = urlThumb;
	}
	
	public UrlsImages(String url, String urlThumb, String legenda) {
		super();
		this.dataUrl = Calendar.getInstance().getTime();
		this.url = url;
		this.urlThumb = urlThumb;
		this.legenda = legenda;
	}
	
	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public Long getIdurl() {
		return idurl;
	}

	public void setIdurl(Long idurl) {
		this.idurl = idurl;
	}

	public Date getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(Date dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
