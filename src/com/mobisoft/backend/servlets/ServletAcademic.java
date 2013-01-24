package com.mobisoft.backend.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jivesoftware.smack.XMPPException;

import com.empresa.backend.dao.ManagerDAO;
import com.empresa.backend.entities.MensagemBroadcast;
import com.empresa.backend.regra.SistemaAcademic;
import com.mobway.backend.im.XMPPUtil;

/**
 * Servlet implementation class ServletAcademic
 */
@WebServlet(description = "AutorizadorSistemaAcademic", urlPatterns = { "/academic" })
public class ServletAcademic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SistemaAcademic sa;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAcademic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		super.init();
		new ManagerDAO(); //para criar a factory
		this.sa = new SistemaAcademic();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer tipoReq;
		
		tipoReq = Integer.parseInt(request.getParameter("tipo"));
		
		switch (tipoReq) {
		case 1 :
			String data = request.getParameter("data");
			response.getWriter().append(this.sa.getJsonBackupMsg(data));
			break;
		case 2 :
			String mensagem;
			MensagemBroadcast msgBdst;
			mensagem = request.getParameter("mensagem");
			//inserindo no bd
			msgBdst = new MensagemBroadcast(mensagem);
			this.sa.getMgsDao().insert(msgBdst);
			//enviando para todos os clientes
			try {
				XMPPUtil.enviarMsg(mensagem);
			} catch (XMPPException e) {
				response.sendError(530);
			}
			break;
		default:
			response.sendError(520);
			break;
		}
		
		//response.sendError(530);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}


}
