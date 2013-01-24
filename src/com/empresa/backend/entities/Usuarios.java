package com.empresa.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="votty_usuarios")
public class Usuarios {

	public Usuarios(String nome, String imei) {
		super();
		this.nome = nome;
		this.imei = imei;
	}
	
	@GeneratedValue
	@Id
	Long idUsuario;
	
	@Column(name="nome")
	String nome;
	
	@Column(name="imei")
	String imei;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

}
