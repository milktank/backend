package com.empresa.backend.dao;



import java.util.List;

import org.hibernate.Filter;
import org.hibernate.classic.Session;
import com.empresa.backend.entities.UrlsImages;

public class UrlsImagesDAO implements DAOIF<UrlsImages>{

	public List<UrlsImages> getAll(){
		
		//Obtendo sessao
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UrlsImages> l = (List<UrlsImages>)s.createCriteria(UrlsImages.class);
		
		//finalizando transacao
		s.getTransaction().commit();
		s.close();
		
		return l;
	}
	
	public void insert(UrlsImages d){
		
		//Obtendo sessao
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		s.beginTransaction();
		
		s.save(d);
		
		s.getTransaction().commit();
		
		s.close();
	}

	@Override
	public UrlsImages searchOne(UrlsImages model) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<UrlsImages> imagensPorEmpresa(Long idEmpresa){
		
		//Obtendo sessao
		Session s = ManagerDAO.factory.openSession();
		
		//iniciando transacao e obtendo todos os objetos
		Filter f = s.enableFilter("porEmpresa");
		System.out.println(f.getFilterDefinition().getParameterNames());
		System.out.println(f.getFilterDefinition().getParameterTypes());
		f.setParameter("empresa", idEmpresa);
		@SuppressWarnings("unchecked")
		List<UrlsImages> l = (List<UrlsImages>)(s.createCriteria(UrlsImages.class)).list();
		s.beginTransaction();		
		
		//finalizando transacao
		s.getTransaction().commit();
		s.close();
		
		return l;
	}
}
