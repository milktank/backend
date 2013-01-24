package com.empresa.backend.dao;

import java.util.List;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.empresa.backend.entities.Usuarios;

public class UsuarioDAO implements DAOIF<Usuarios>{

	public UsuarioDAO() {
		
		
	}

	public List<Usuarios> getAll(){
		
		//Obtendo sess�o
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Usuarios> l = (List<Usuarios>)s.createCriteria(Usuarios.class);
		
		//finaliizando transacao
		s.getTransaction().commit();
		s.close();
		
		return l;
	}
	
	public void insert(Usuarios u){
		
		//Obtendo sessao
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		
		s.save(u);
		
		s.getTransaction().commit();
		
		s.close();
	}

	@Override
	public Usuarios searchOne(Usuarios model) {

		//Obtendo sess�o
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Usuarios> l = (List<Usuarios>)s.createCriteria(Usuarios.class).add(Restrictions.eq("imei", model.getImei())).setMaxResults(1);
		
		//finaliizando transacao
		s.getTransaction().commit();
		s.close();
		
		return l.get(0);
	}
}
