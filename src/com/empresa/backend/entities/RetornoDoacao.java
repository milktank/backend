package com.empresa.backend.entities;

public class RetornoDoacao {

	private String urlRetorno;
	private Integer idDoacao;
	
		public RetornoDoacao(String urlRetorno, Integer idDoacao) {
		super();
		this.urlRetorno = urlRetorno;
		this.idDoacao = idDoacao;
	}

		public String getUrlRetorno() {
			return urlRetorno;
		}

		public void setUrlRetorno(String urlRetorno) {
			this.urlRetorno = urlRetorno;
		}

		public Integer getIdDoacao() {
			return idDoacao;
		}

		public void setIdDoacao(Integer idDoacao) {
			this.idDoacao = idDoacao;
		}
}
