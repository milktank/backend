package com.empresa.backend.regra;

import java.util.Vector;

public class Utils {

	public static Vector<String> split(String string, String quebra){ 
		Vector<String> aux = new Vector<String>();
		int index = 0;
		int prox = 0;
		while((index = string.indexOf(quebra, prox)) >= 0){
			aux.addElement(string.substring(prox, index));
			prox = index + 1;
		}
		aux.addElement(string.substring(prox));
		System.gc();
		return aux;
	}

	public static int qtdeLinhas(String destino){
		int qtde;
		
		qtde = destino.length()/10;
		
		if(qtde == 0)
			qtde = 1;
		
		return qtde; 
	}

	public static String[] quebrarDestino(String destino, int linhas){
		int linhasLidas;
		String linha;
		String[] retorno;

		/* quantidade de linhas */
		linhasLidas= 0;

		if(linhas> 1){
			retorno = new String[linhas];

			/* loop para inser��o das linhas */
			for(int i= 0; i< linhas; i++){
				linha = destino.substring((linhasLidas*10)+1, (linhasLidas*10)+10+1);
				linhasLidas+= 1;
				retorno[i]= linha;
			}
		}else{
			retorno= new String[1];
			retorno[0]= destino;
		}

		return retorno;
	}

}
