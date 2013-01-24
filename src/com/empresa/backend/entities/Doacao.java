package com.empresa.backend.entities;

import java.util.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mobway_doacoes")
public class Doacao {

	@Column(name="id_doacao")
	@GeneratedValue
	@Id
	Long idDoacao;
	
	@ManyToOne
	@JoinColumn(name="fk_id_usuario")
	Usuarios doador;

	@Column(name="valor_doacao")
	Float valor;
	
	@Column(name="momento_doacao")
	Date momento_doacao;
	
	@Column(name="codigo_pagseguro")
	String codigo_pagseguro;
	
	@Column(name="pendente")
	Boolean pendente;

	public Doacao(Long idDoacao, Usuarios doador, Float valor,
			Date momento_doacao, String codigo, Boolean pendente) {
		super();
		this.idDoacao = idDoacao;
		this.doador = doador;
		this.valor = valor;
		this.momento_doacao = momento_doacao;
		this.codigo_pagseguro = codigo;
		this.pendente = pendente;
	}
	

	public Doacao(Usuarios doador, Float valor) {
		super();
		this.doador = doador;
		this.valor = valor;
		this.momento_doacao = Calendar.getInstance().getTime();
	}
	
	public Doacao(Usuarios doador, Float valor, String codigo, Boolean pendente, Date momentoDoacao) {
		super();
		this.doador = doador;
		this.valor = valor;
		this.codigo_pagseguro = codigo;
		this.pendente = pendente;
		this.momento_doacao = momentoDoacao;
	}

	public Long getIdDoacao() {
		return idDoacao;
	}

	public void setIdDoacao(Long idDoacao) {
		this.idDoacao = idDoacao;
	}

	public Usuarios getDoador() {
		return doador;
	}

	public void setDoador(Usuarios doador) {
		this.doador = doador;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getMomento_doacao() {
		return momento_doacao;
	}

	public void setMomento_doacao(Date momento_doacao) {
		this.momento_doacao = momento_doacao;
	}


	public String getCodigo_pagseguro() {
		return codigo_pagseguro;
	}


	public void setCodigo_pagseguro(String codigo_pagseguro) {
		this.codigo_pagseguro = codigo_pagseguro;
	}
}
