package com.mobisoft.backend.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;

import br.com.uol.pagseguro.exception.PagSeguroServiceException;

import com.empresa.backend.comunicacao.CreateJSONXStream;
import com.empresa.backend.comunicacao.URLsFotos;
import com.empresa.backend.dao.DoacaoDAO;
import com.empresa.backend.dao.ManagerDAO;
import com.empresa.backend.dao.UrlsImagesDAO;
import com.empresa.backend.dao.UsuarioDAO;
import com.empresa.backend.entities.Doacao;
import com.empresa.backend.entities.RetornoDoacao;
import com.empresa.backend.entities.Usuarios;
import com.empresa.backend.regra.Noticias;
import com.empresa.backend.regra.SistemaPagamento;
import com.empresa.backend.regra.TwitterNegocio;
import com.twitterapime.search.LimitExceededException;

/**
 * Servlet implementation class ServletDoacao
 */
@WebServlet(description = "Servlet para doacao de valores via pagseguro", urlPatterns = { "/donate" })
public class ServletDoacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SistemaPagamento sp;
	private TwitterNegocio tn;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDoacao() {
        super();

    }

	public SistemaPagamento getSp() {
		return sp;
	}

	public void setSp(SistemaPagamento sp) {
		this.sp = sp;
	}

	public TwitterNegocio getTn() {
		return tn;
	}

	public void setTn(TwitterNegocio tn) {
		this.tn = tn;
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		new ManagerDAO(); //para criar a factory
		this.sp = new SistemaPagamento();
		try {
			this.tn = new TwitterNegocio();
		} catch (com.empresa.backend.regra.TwitterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LimitExceededException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		try {
			this.sp.getRelatorioCredencial();
		} catch (PagSeguroServiceException e) {
			e.printStackTrace();
		}
		//UrlsImagesDAO udao = new UrlsImagesDAO();
/*		

			http://img225.imageshack.us/img225/6954/sdfim.jpg
			http://img225.imageshack.us/img225/6954/sdfim.th.jpg

			http://img209.imageshack.us/img209/7085/prosperidade.jpg
			http://img209.imageshack.us/img209/7085/prosperidade.th.jpg

			http://img217.imageshack.us/img217/9089/liberdade1.jpg
			http://img217.imageshack.us/img217/9089/liberdade1.th.jpg

			http://img837.imageshack.us/img837/885/imagemreflexiva112.jpg
			http://img837.imageshack.us/img837/885/imagemreflexiva112.th.jpg

			http://img41.imageshack.us/img41/3033/hospitalparaiba.jpg
			http://img41.imageshack.us/img41/3033/hospitalparaiba.th.jpg

			http://img692.imageshack.us/img692/7253/hospitalnapoleaolaurean.jpg
			http://img692.imageshack.us/img692/7253/hospitalnapoleaolaurean.th.jpg

			http://img24.imageshack.us/img24/5041/72293644.jpg
			http://img24.imageshack.us/img24/5041/72293644.th.jpg

			http://img814.imageshack.us/img814/3385/73396843.jpg
			http://img814.imageshack.us/img814/3385/73396843.th.jpg

			http://img443.imageshack.us/img443/6677/49990143.jpg
			http://img443.imageshack.us/img443/6677/49990143.th.jpg

			http://img198.imageshack.us/img198/7681/54833123.jpg
			http://img198.imageshack.us/img198/7681/54833123.th.jpg

			http://img840.imageshack.us/img840/2066/30565557.jpg
			http://img840.imageshack.us/img840/2066/30565557.th.jpg

			http://img220.imageshack.usimg220/3635/10599031.jpg", "http://img220.imageshack.us/img220/3635/10599031.th.jpg

			http://img839.imageshack.us/img839/8617/67497139.png", "http://img839.imageshack.us/img839/8617/67497139.th.png

			http://img821.imageshack.us/img821/6577/19290777.gif", "http://img821.imageshack.us/img821/6577/19290777.th.gif

			http://img268.imageshack.us/img268/2228/90804129.jpg", "http://img268.imageshack.us/img268/2228/90804129.th.jpg

			http://img38.imageshack.us/img38/1029/56208591.jpg", "http://img38.imageshack.us/img38/1029/56208591.th.jpg

			http://img820.imageshack.us/img820/3676/93307950.jpg", "http://img820.imageshack.us/img820/3676/93307950.th.jpg

			http://img705.imageshack.us/img705/7595/90900328.jpg", "http://img705.imageshack.us/img705/7595/90900328.th.jpg

			http://img685.imageshack.us/img685/9766/58677308.jpg", "http://img685.imageshack.us/img685/9766/58677308.th.jpg
			
			http://img26.imageshack.us/img26/4161/tratamentocuracancer.jpg", "http://img26.imageshack.us/img26/4161/tratamentocuracancer.th.jpg

			http://img96.imageshack.us/img96/342/95620131.jpg", "http://img96.imageshack.us/img96/342/95620131.th.jpg

			http://img52.imageshack.us/img52/4779/84199205.jpg", "http://img52.imageshack.us/img52/4779/84199205.th.jpg

			http://img198.imageshack.us/img198/2337/16947970.jpg", "http://img198.imageshack.us/img198/2337/16947970.th.jpg

			http://img706.imageshack.us/img706/3782/24876844.jpg", "http://img706.imageshack.us/img706/3782/24876844.th.jpg

			http://img641.imageshack.us/img641/1/47794007.jpg", "http://img641.imageshack.us/img641/1/47794007.th.jpg

			http://img525.imageshack.us/img525/1019/99741596.jpg", "http://img525.imageshack.us/img525/1019/99741596.th.jpg

			http://img715.imageshack.us/img715/5673/90886627.jpg, http://img715.imageshack.us/img715/5673/90886627.th.jpg

			http://img818.imageshack.us/img818/3576/35269650.jpg, http://img818.imageshack.us/img818/3576/35269650.th.jpg

			http://img341.imageshack.us/img341/9639/37605780.jpg, http://img341.imageshack.us/img341/9639/37605780.th.jpg

			http://img577.imageshack.us/img577/3955/22202696.jpg, http://img577.imageshack.us/img577/3955/22202696.th.jpg*/
//		udao.insert(new UrlsImages("http://img26.imageshack.us/img26/4161/tratamentocuracancer.jpg", "http://img26.imageshack.us/img26/4161/tratamentocuracancer.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img577.imageshack.us/img577/3955/22202696.jpg", "http://img577.imageshack.us/img577/3955/22202696.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img341.imageshack.us/img341/9639/37605780.jpg", "http://img341.imageshack.us/img341/9639/37605780.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img818.imageshack.us/img818/3576/35269650.jpg", "http://img818.imageshack.us/img818/3576/35269650.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img715.imageshack.us/img715/5673/90886627.jpg", "http://img715.imageshack.us/img715/5673/90886627.th.jpg", "Tratamento Cura"));		
//		udao.insert(new UrlsImages("http://img641.imageshack.us/img641/1/47794007.jpg", "http://img641.imageshack.us/img641/1/47794007.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img706.imageshack.us/img706/3782/24876844.jpg", "http://img706.imageshack.us/img706/3782/24876844.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img198.imageshack.us/img198/2337/16947970.jpg", "http://img198.imageshack.us/img198/2337/16947970.th.jpg", "Tratamento Cura"));		
//		udao.insert(new UrlsImages("http://img52.imageshack.us/img52/4779/84199205.jpg", "http://img52.imageshack.us/img52/4779/84199205.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img96.imageshack.us/img96/342/95620131.jpg", "http://img96.imageshack.us/img96/342/95620131.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img705.imageshack.us/img705/7595/90900328.jpg", "http://img705.imageshack.us/img705/7595/90900328.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img26.imageshack.us/img26/4161/tratamentocuracancer.jpg", "http://img26.imageshack.us/img26/4161/tratamentocuracancer.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img820.imageshack.us/img820/3676/93307950.jpg", "http://img820.imageshack.us/img820/3676/93307950.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img38.imageshack.us/img38/1029/56208591.jpg", "http://img38.imageshack.us/img38/1029/56208591.th.jpg", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img268.imageshack.us/img268/2228/90804129.jpg", "http://img268.imageshack.us/img268/2228/90804129.th.jpg", "Tratamento Cura"));udao.insert(new UrlsImages("http://img821.imageshack.us/img821/6577/19290777.gif", "http://img821.imageshack.us/img821/6577/19290777.th.gif", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img839.imageshack.us/img839/8617/67497139.png", "http://img839.imageshack.us/img839/8617/67497139.th.png", "Tratamento Cura"));
//		udao.insert(new UrlsImages("http://img220.imageshack.usimg220/3635/10599031.jpg", "http://img220.imageshack.us/img220/3635/10599031.th.jpg", "Tratamento Cura"));
//		CreateJSONXStream cj = new CreateJSONXStream();
	}

//	private String retornoTeste(){
//		
//		List<UrlsImages> urls = new ArrayList<UrlsImages>();
//		
//		urls.add(new UrlsImages("http://bobagento.com/wp-content/uploads/2009/04/teste-19-04-08.jpg", "data:image/jpeg;base64,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"));
//		urls.add(new UrlsImages("http://bp1.blogger.com/_qYf_PP6fc1E/R2-gGHMU3QI/AAAAAAAABN8/CU1Qx7LgIUA/s400/paciencia.jpg", "https://encrypted-tbn0.google.com/images?q=tbn:ANd9GcRC9AxNfTSQz781OSbUceHdm8qv7ae0aQMy5AmYQvhOQ9A-eN2f5w"));
//		
//		CreateJSONXStream cj = new CreateJSONXStream();
//		return cj.getXs().toXML(new URLsFotos(urls));
//	}
	
	private String ultimasNoticias(){
		
		CreateJSONXStream cj = new CreateJSONXStream();
		
		try {
			return cj.getXs().toXML(new Noticias(this.tn.getNoticias()));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer tipo = 0;
		CreateJSONXStream cj = new CreateJSONXStream();
		
		//System.out.println(request.getParameterMap());
		
		tipo =  Integer.parseInt(request.getParameter("t"));
		
		switch (tipo) {
			case 1 :
				UrlsImagesDAO udao = new UrlsImagesDAO();
				response.getWriter().append(cj.getXs().toXML(new URLsFotos(udao.imagensPorEmpresa(32l))));
				return;
			case 2 :
//				System.out.println(cj.getXs().toXML(this.ultimasNoticias()));
				response.getWriter().append(this.ultimasNoticias());
				return;
			case 3 :
				Float valor;
				String item;
				Usuarios u;
				Doacao d = null;
				
				//registrar transacao no banco de dados
				DoacaoDAO dDao = new DoacaoDAO();
				UsuarioDAO uDao = new UsuarioDAO();
				u = new Usuarios(request.getParameter("nome"), request.getParameter("email"));
				
				uDao.insert(u);
				valor = Float.valueOf(request.getParameter("valor")) / 100f;
				d = new Doacao(u, valor);
				dDao.insert(d);
				item = "DoacaoLaureano";
				
			//	System.out.println(d.getIdDoacao());
				
				String urlRetorno = this.sp.efetuarPagamento(valor, d.getIdDoacao().doubleValue(), item, u, request.getParameter("email"), request.getParameter("ddd"), request.getParameter("telefone"));
				System.out.println(urlRetorno);
				if(urlRetorno == null){
					response.sendError(520);
					return;
				}
				d.setCodigo_pagseguro(urlRetorno);
				response.getWriter().append(cj.getXs().toXML(new RetornoDoacao(urlRetorno, d.getIdDoacao().intValue())));
				return;
			case 4 :
//				System.out.println(cj.getXs().toXML(this.ultimasNoticias()));
				DoacaoDAO ddao = new DoacaoDAO();
				response.getWriter().append(ddao.getSomaIntervalo().toString());
				return;
			default :
				response.sendError(510);
				return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
