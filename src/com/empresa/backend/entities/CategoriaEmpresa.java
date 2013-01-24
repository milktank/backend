package com.empresa.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mobway_categoria_empresa")
public class CategoriaEmpresa {

	@Id
	@Column(name="id_categoria_empresa")
	@GeneratedValue
	Long id;
	
	@Column(name="nome")
	String nome;
	
	@Column(name="descricao")
	String descricao;

	public CategoriaEmpresa() {
		super();
	}

	public CategoriaEmpresa(Long id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
