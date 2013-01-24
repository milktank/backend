package com.empresa.backend.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name="mobway_backup_msg")
@FilterDef(name="dataBase", parameters=@ParamDef( name="data", type="timestamp" ))
@Filters( {
    @Filter(name="dataBase", condition="data_msg < :data")
} )
public class MensagemBroadcast {

	@Id
	@GeneratedValue
	@Column(name="id_backup_msg")
	Long idMsg;
	
	@Column(name="data_msg")
	Date dataMsg;
	
	@Column(name="mensagem")
	String mensagem;

	public MensagemBroadcast() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MensagemBroadcast(Long idMsg, Date dataMsg, String mensagem) {
		super();
		this.idMsg = idMsg;
		this.dataMsg = dataMsg;
		this.mensagem = mensagem;
	}
	
	public MensagemBroadcast(Date dataMsg) {
		super();
		this.dataMsg = dataMsg;
	}
	
	public MensagemBroadcast(String mensagem) {
		super();
		this.dataMsg = Calendar.getInstance().getTime();
		this.mensagem = mensagem;
	}

	public Long getIdMsg() {
		return idMsg;
	}

	public void setIdMsg(Long idMsg) {
		this.idMsg = idMsg;
	}

	public Date getDataMsg() {
		return dataMsg;
	}

	public void setDataMsg(Date dataMsg) {
		this.dataMsg = dataMsg;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
