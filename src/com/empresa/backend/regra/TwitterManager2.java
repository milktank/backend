package com.empresa.backend.regra;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterManager2 {
	
	static final public String USER_NAME = "vcContraOCancer";
	
	private Twitter twitter;
	
	public void logar() throws TwitterException {
		
		this.twitter = new TwitterFactory().getInstance();
	    
	    System.out.println("ID" + twitter.verifyCredentials().getId());
	  }
	
	public List<Status> getTweetsByUser(String userName, Integer qtd) throws TwitterException {
		
		Paging paging = new Paging(1, qtd);
		return twitter.getUserTimeline(userName, paging);
	}
	
	public void tweetIt(String tweet) throws TwitterException{
		
		twitter.updateStatus(tweet);
	}
}
