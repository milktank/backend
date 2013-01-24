package com.empresa.backend.dao;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.classic.Session;

import com.empresa.backend.entities.MensagemBroadcast;

public class MensagemBroadcastDAO implements DAOIF<MensagemBroadcast>{

	public MensagemBroadcastDAO() {
		
		
	}

	public List<MensagemBroadcast> getAll(){
		
		//Obtendo sessao
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<MensagemBroadcast> l = (List<MensagemBroadcast>)s.createCriteria(MensagemBroadcast.class);
		
		//finaliizando transacao
		s.getTransaction().commit();
		s.close();
		
		return l;
	}
	
	public void insert(MensagemBroadcast msg){
		
		//Obtendo sess�o
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		
		s.save(msg);
		
		s.getTransaction().commit();
		
		s.close();
	}

	
	
	public List<MensagemBroadcast> searchBackup(MensagemBroadcast model) {

		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		Filter f = s.enableFilter("dataBase");
		f.setParameter("data", model.getDataMsg());
		@SuppressWarnings("unchecked")
		List<MensagemBroadcast> l = (List<MensagemBroadcast>)s.createCriteria(MensagemBroadcast.class).list();
		
		//finaliizando transacao
		s.getTransaction().commit();
		s.close();
		
		return l;
	}

	@Override
	public MensagemBroadcast searchOne(MensagemBroadcast model) {
		// TODO Auto-generated method stub
		return null;
	}
	
}