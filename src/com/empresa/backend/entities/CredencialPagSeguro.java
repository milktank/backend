package com.empresa.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mobway_credenciais_pagseguro")
public class CredencialPagSeguro {

	public CredencialPagSeguro(Long id, String token, String email) {
		super();
		this.id = id;
		this.token = token;
		this.email = email;
	}

	public CredencialPagSeguro() {
		super();
	}
	
	@Id
	@Column(name="id_credencial_pagseguro")
	@GeneratedValue
	Long id;
	
	@Column(name="token")
	String token;
	
	@Column(name="email")
	String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
