package com.empresa.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="mobsoft_empresas")
public class Empresas {

	@Column(name="nome")
	String nome;
	
	@Column(name="cnpj")
	String cnpj;
	
	@Column(name="endereco")
	String endereco;
	
	@GeneratedValue
	@Id
	@Column(name="id_empresa")
	Long id_empresa;
	
	@OneToOne
	@JoinColumn(name="fk_redes_sociais")
	RedesSociais redes_sociais;
	
	@OneToOne
	@JoinColumn(name="fk_credencial_pagseguro")
	CredencialPagSeguro credencial_pagseguro;
	
	@ManyToOne
	@JoinColumn(name="fk_categoria_empresa")
	CategoriaEmpresa categoria;
	
	public Empresas() {
		super();
	}

	public Empresas(String nome, String cnpj, String endereco) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Long id_empresa) {
		this.id_empresa = id_empresa;
	}

	public RedesSociais getRedes_sociais() {
		return redes_sociais;
	}

	public void setRedes_sociais(RedesSociais redes_sociais) {
		this.redes_sociais = redes_sociais;
	}

	public CredencialPagSeguro getCredencial_pagseguro() {
		return credencial_pagseguro;
	}

	public void setCredencial_pagseguro(CredencialPagSeguro credencial_pagseguro) {
		this.credencial_pagseguro = credencial_pagseguro;
	}
}
