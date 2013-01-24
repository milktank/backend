package com.empresa.backend.regra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

import com.twitterapime.search.LimitExceededException;

public class TwitterNegocio {

	private static final int QTD_NoticiaS_MAX = 20;
	private TwitterManager2 tm;
	
	public TwitterNegocio() throws TwitterException, IOException, LimitExceededException, twitter4j.TwitterException{
		
		tm = new TwitterManager2();
		tm.logar();
	}

	public List<Noticia> getNoticias() throws twitter4j.TwitterException{
		
		List<Status> v = tm.getTweetsByUser(TwitterManager.USER_NAME, QTD_NoticiaS_MAX);
		List<Noticia> noticias = new ArrayList<Noticia>();
		
		System.out.println(v.size());
		
		for(Status s : v){
			//System.out.println("Tweet: " + s.getText());
			noticias.add(this.montarNoticia(s.getText()));
		}
		
		return noticias;
	}
	
	private Noticia montarNoticia(String twitter){

		Noticia c;

		//List<String> dados = Utils.split(twitter, " - ");
		String[] dados = twitter.split(" - ");
		if(dados.length < 2)
			c = new Noticia(twitter, null);
		else
			c = new Noticia((String)dados[0], (String)dados[1]);

		return c;
	}
	

	public void publicarNoticia(Noticia c) throws IOException, LimitExceededException, twitter4j.TwitterException{
		
		this.tm.tweetIt(this.getTweetNoticia(c));
	}
	
	private String getTweetNoticia(Noticia c){
	
		return c.getTexto() + " - " + c.getUrlTiny(); 
	} 
}
