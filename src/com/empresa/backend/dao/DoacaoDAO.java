package com.empresa.backend.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;

import com.empresa.backend.entities.Doacao;

public class DoacaoDAO implements DAOIF<Doacao>{

	public DoacaoDAO() {
		
	}
	
	

	public List<Doacao> getAll(){
		
		//Obtendo sessao
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Doacao> l = (List<Doacao>)s.createCriteria(Doacao.class).list();
		//finalizando transacao
		s.getTransaction().commit();
		s.close();
		
		return l;
	}
	
	public Float getSomaIntervalo(){
		
		//Obtendo sessao
		Session s = ManagerDAO.factory.openSession();
		Criteria c = s.createCriteria(Doacao.class);
		Float soma;
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		//@SuppressWarnings("unchecked")
		soma = (Float)(c.setProjection(Projections.sum("valor")).uniqueResult());
		//finalizando transacao
		s.getTransaction().commit();
		s.close();
		
		return soma == null?0:soma;
	}  
	
	
	
	public void insert(Doacao d){
		
		//Obtendo sessao
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		
		s.save(d);
		
		s.getTransaction().commit();
		
		s.close();
	}

	@Override
	public Doacao searchOne(Doacao model) {

		return null;
	}
}
