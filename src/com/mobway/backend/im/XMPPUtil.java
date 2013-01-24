package com.mobway.backend.im;
/*
* Copyright (c) 2011 by Avaty! Tecnologia.
* Avaty! Tecnologia Confidential Proprietary
* This document and the information contained in it is
* CONFIDENTIAL INFORMATION of Avaty! Tecnologia, and shall not
* be used, or published, or disclosed, or disseminated
* outside of Avaty! Tecnologia in whole or in part without
* Avaty! Tecnologia's consent. This document contains trade
* secrets of Avaty! Tecnologia. Reverse engineering of any or
* all of the information in this document is prohibited.
* The copyright notice does not imply publication of
* this document.
*
*/



import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class XMPPUtil {

	private static XMPPConnection con = null;

	public static void enviarMsg(String mensagem) throws XMPPException {
		login();
		sendBroadcastMessage(mensagem);
		disconnect();
	}

	private static void login() throws XMPPException {
		ConnectionConfiguration conf = new ConnectionConfiguration(Constantes.HOST, Constantes.PORT,
				Constantes.SERVER_NAME);
		con = new XMPPConnection(conf);
		if (!con.isConnected())
			con.connect();
		System.out.println("connected");
		if (!con.isAuthenticated())
			System.out.println("authenticated");
			con.login(Constantes.LOGIN, Constantes.PWD);
	}

	private static void sendBroadcastMessage(String msg) throws XMPPException {
		Chat chat = con.getChatManager().createChat("all@broadcast."+Constantes.NAME,
				new MessageListener() {

					@Override
					public void processMessage(Chat chat, Message msg) {

					}

				});
		chat.sendMessage(msg);
		System.out.println("sended");
	}

	private static void disconnect() {
		System.out.println("desconnected");
		con.disconnect();
	}

}
