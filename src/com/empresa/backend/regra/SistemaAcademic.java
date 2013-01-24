package com.empresa.backend.regra;

import java.sql.Date;
import java.util.Calendar;

import com.empresa.backend.comunicacao.BackupMsgBroadcast;
import com.empresa.backend.comunicacao.CreateJSONXStream;
import com.empresa.backend.dao.MensagemBroadcastDAO;
import com.empresa.backend.entities.MensagemBroadcast;

public class SistemaAcademic {

	private MensagemBroadcastDAO mgsDao;

	public SistemaAcademic() {
		super();
		this.mgsDao = new MensagemBroadcastDAO();
	}

	public MensagemBroadcastDAO getMgsDao() {
		return mgsDao;
	}

	public void setMgsDao(MensagemBroadcastDAO mgsDao) {
		this.mgsDao = mgsDao;
	}
	
	public String getJsonBackupMsg(String data){
				
		//transformar string em calendar
		System.out.println(data);
		String date = data.split(" ")[0];
		String hora = data.split(" ")[1];
		Calendar c = Calendar.getInstance();
		c.setTime(Date.valueOf(date)); //setando data no calendar
		//formatando hora
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora.split(":")[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(hora.split(":")[1]));
		
		
		CreateJSONXStream cj = new CreateJSONXStream();
		return cj.getXs().toXML(new BackupMsgBroadcast(this.getMgsDao().searchBackup(new MensagemBroadcast(c.getTime()))));
	}
}
