package com.empresa.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mobway_redes_sociais")
public class RedesSociais {

	@Id
	@Column(name="id_redes_sociais")
	@GeneratedValue
	Long id;
	
	@Column(name="facebook")
	String facebook;
	
	@Column(name="twitter")
	String twitter;
	
	@Column(name="flicker")
	String flicker;

	@Column(name="linkedin")
	String linkedin;
	
	@Column(name="instagram")
	String instagram;
	
	@Column(name="youtube")
	String youtube;
	
	public RedesSociais() {
		super();
	}

	public RedesSociais(Long id, String facebook, String twitter,
			String flicker, String linkedin, String instagram, String youtube) {
		super();
		this.id = id;
		this.facebook = facebook;
		this.twitter = twitter;
		this.flicker = flicker;
		this.linkedin = linkedin;
		this.instagram = instagram;
		this.youtube = youtube;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFlicker() {
		return flicker;
	}

	public void setFlicker(String flicker) {
		this.flicker = flicker;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

}
