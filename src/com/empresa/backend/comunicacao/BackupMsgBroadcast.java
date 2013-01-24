package com.empresa.backend.comunicacao;

import java.util.List;

import com.empresa.backend.entities.MensagemBroadcast;

public class BackupMsgBroadcast {

	private List<MensagemBroadcast> msgs;

	public BackupMsgBroadcast(List<MensagemBroadcast> msgs) {
		super();
		this.msgs = msgs;
	}

	public List<MensagemBroadcast> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<MensagemBroadcast> msgs) {
		this.msgs = msgs;
	}
	
}
