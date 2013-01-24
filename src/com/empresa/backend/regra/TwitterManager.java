package com.empresa.backend.regra;

import java.io.IOException;
import java.util.Vector;

import com.twitterapime.rest.Credential;
import com.twitterapime.rest.Timeline;
import com.twitterapime.rest.TweetER;
import com.twitterapime.rest.UserAccountManager;
import com.twitterapime.search.LimitExceededException;
import com.twitterapime.search.Query;
import com.twitterapime.search.QueryComposer;
import com.twitterapime.search.SearchDeviceListener;
import com.twitterapime.search.Tweet;
import com.twitterapime.xauth.Token;

public class TwitterManager {
	
	/* ACESSO A CONTA @fahrtsys_mobsoft */
	static final public String USER_NAME = "vcContraOCancer";
	static final private String OAUTH_TOKEN = "706959942-lEVctV6aqI0uxf6dMOo5VUoAQR80flYPn9wlYhoT";
	static final private String OAUTH_TOKEN_SECRET = "fVNRClUxnvSQBRR2b6gWovIGFiCMFhz9NVpGuWdtrTE";
	
	/* IDENTIFICA��O APLICATIVO */
	static final private String CONSUMER_KEY = "s0RdIiQ9gXpXxhCS99nfA";
	static final private String CONSUMER_SECRET = "89shJ2hlHz1bCfRqgSYmBbXBOdet9sqW0GrwryNUoN0";
	
	public static final int CNT_MAX_TWEET = 140;
	
	private UserAccountManager accountMan;
	
	public TwitterManager() throws TwitterException, IOException, LimitExceededException {

		accountMan = this.logar();
		if(!accountMan.verifyCredential())
		throw new TwitterException("Erro ao logar");
	}
	
	private UserAccountManager logar(){
		
		Token token = new Token(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
		//Credential c = new Credential(CONSUMER_KEY, CONSUMER_SECRET, token);
		Credential c = new Credential(USER_NAME, CONSUMER_KEY, CONSUMER_SECRET, token);
		
		//Credential c = new Credential(USER_NAME, "99784087", CONSUMER_KEY, CONSUMER_SECRET);

		UserAccountManager m = UserAccountManager.getInstance(c);
		
		return m;
	}
	
	public void tweetIt(String t) throws IOException, LimitExceededException {
		
		TweetER ter = TweetER.getInstance(this.accountMan);
		
		if(t.length() > CNT_MAX_TWEET){
			int qtde = (t.length() % CNT_MAX_TWEET > 0)?(t.length() / CNT_MAX_TWEET + 1):(t.length() / CNT_MAX_TWEET);
			for(int i = 0; i < qtde; i++){
				Tweet twt = new Tweet(t.substring(i * 135, ((i + 1) * 135 > t.length())?((i + 1) * 135):(t.length())) + " (+)");
				ter.post(twt);
			}
		} else {
			Tweet twt = new Tweet(t);
			ter.post(twt);
		}
	}
	
	public Vector<String> getTweetsByUser(String user){
		
		return this.getTweetsByUser(user, 0);
	}
	
	public Vector<String> getTweetsByUser(String user, int qtdMax){
		
		final Vector<String> v = new Vector<String>();
		Vector<String> retorno = new Vector<String>();
		
		Timeline tml = Timeline.getInstance(this.accountMan);
		Query q = (qtdMax > 0) ? (QueryComposer.count(qtdMax)) : (null);
		tml.startGetUserTweets(q, 
		new SearchDeviceListener() {
			public void tweetFound(Tweet tweet) {
				System.out.println(tweet.getString("TWEET_CONTENT"));
				v.addElement(tweet.getString("TWEET_CONTENT"));
			}

			public void searchCompleted() {
			}

			public void searchFailed(Throwable arg0) {
					
			}
		});
		
		int tam = v.size(); 
		
		for(int i = 0; i < tam; i++)
			retorno.addElement(v.elementAt(i));

		return retorno;
	}
	
	
}
