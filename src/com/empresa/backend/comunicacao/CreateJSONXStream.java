package com.empresa.backend.comunicacao;

import com.empresa.backend.entities.Doacao;
import com.empresa.backend.entities.RetornoDoacao;
import com.empresa.backend.regra.Noticia;
import com.empresa.backend.regra.Noticias;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;


public class CreateJSONXStream {
	
	private XStream xs;
	
	public CreateJSONXStream(){
		xs = new XStream(new JsonHierarchicalStreamDriver());
		createAlias();
	}

	private void createAlias() {
		
		xs.alias("restaurantes", Estabelecimento.class);
		
		xs.alias("estabelecimentos", Restaurantes.class);
		
		xs.alias("mensagens", BackupMsgBroadcast.class);
		
		xs.alias("doacao", Doacao.class);
		
		xs.alias("fotos", URLsFotos.class);
		
		xs.alias("doacao", RetornoDoacao.class);
		
		xs.alias("noticia", Noticia.class);
		
		xs.alias("noticias", Noticias.class);
	}

	public XStream getXs() {
		return xs;
	}

	public void setXs(XStream xs) {
		this.xs = xs;
	}
}
