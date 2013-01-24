package com.empresa.backend.regra;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Currency;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.domain.TransactionStatus;
import br.com.uol.pagseguro.domain.TransactionSummary;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.TransactionSearchService;

import com.empresa.backend.dao.DoacaoDAO;
import com.empresa.backend.dao.UsuarioDAO;
import com.empresa.backend.entities.Doacao;
import com.empresa.backend.entities.Usuarios;

public class SistemaPagamento {

	private AccountCredentials credencialPagSeguro;
	
	public SistemaPagamento() {
		super();
		this.credencialPagSeguro = PagSeguroConfig.getAccountCredentials();
		//this.credencialPagSeguro = new AccountCredentials("renato@mob-way.com", "AB9E0444B5624D68B0B81B0CC5E1F3C1");  
	}

	public AccountCredentials getCredencialPagSeguro() {
		return credencialPagSeguro;
	}

	public void setCredencialPagSeguro(AccountCredentials credencialPagSeguro) {
		this.credencialPagSeguro = credencialPagSeguro;
	}
	
	@SuppressWarnings("rawtypes")
	public void getRelatorioCredencial() throws PagSeguroServiceException{
		
		// Chama a API  
        TransactionSearchResult result = null;  
  
        try {  
            Calendar baseCalendar = Calendar.getInstance();  
            baseCalendar.set(baseCalendar.get(Calendar.YEAR), 
            		         baseCalendar.get(Calendar.MONTH), 
            		         baseCalendar.get(Calendar.DAY_OF_MONTH), 
            		         0, 0); //HH, MM
            //baseCalendar.add(Calendar.DAY_OF_MONTH, -1);
            Calendar finalCalendar = Calendar.getInstance();  
            finalCalendar.set(baseCalendar.get(Calendar.YEAR), 
            				  baseCalendar.get(Calendar.MONTH), 
            				  baseCalendar.get(Calendar.DAY_OF_MONTH), 
            				  23, 59); //HH, MM
            //finalCalendar.add(Calendar.DAY_OF_MONTH, -1);
            result = TransactionSearchService.searchByDate(this.getCredencialPagSeguro(),  
            											   baseCalendar.getTime(),  
        											   	   finalCalendar.getTime(),   
        											   	   new Integer(1),  
        											   	   new Integer(10));  
        } catch (PagSeguroServiceException e) {  
            System.err.println("ERRO RELATORIO: " + e.toString());  
            return;  
        }  
  
        if (result != null) {  
            System.out.println("Data de realizacao da busca: " + result.getDate());  
  
            System.out.println("Exibindo " + result.getResultsInThisPage() +  
                " resultado(s) nesta pagina de numero " + result.getPage() +   
                " de um total de " + result.getTotalPages() + " pagina(s).");  
  
			List listTransactionSummaries = result.getTransactionSummaries();  
            Iterator transactionSummariesIterator = listTransactionSummaries.iterator();  
  
            while (transactionSummariesIterator.hasNext()) { 
            	TransactionSummary ts = (TransactionSummary)transactionSummariesIterator.next();
            	
            	ts.getCode();
            	
            	try {
					Transaction t = TransactionSearchService.searchByCode(this.getCredencialPagSeguro(), ts.getCode());
					System.out.println(t);
					//encontrar doador ja cadastrado
					UsuarioDAO uDao = new UsuarioDAO();
					Usuarios u = uDao.searchOne(new Usuarios(t.getSender().getName(), t.getSender().getEmail()));
					DoacaoDAO dDao = new DoacaoDAO();
					dDao.insert(new Doacao(u, t.getFeeAmount().floatValue(), t.getCode(), (t.getStatus() == TransactionStatus.AVAILABLE)?true:false, t.getDate()));
				} catch (PagSeguroServiceException e) {
					e.printStackTrace();
					throw e;
				}	
            }
        }
	}

	public String efetuarPagamento(Float valor, Double id, String item, Usuarios u, String email, String ddd, String telefone) {
		
		// Instantiate a new payment request
		PaymentRequest paymentRequest = new PaymentRequest();
		// Sets the currency
		paymentRequest.setCurrency(Currency.BRL);
		// Add an item for this payment request
		paymentRequest.addItem(Double.toString(id), item,  new Integer(1), (new BigDecimal(valor)).setScale(2, RoundingMode.HALF_UP), null, null);
		// Sets a reference code for this payment request, it's useful to identify this payment in future notifications.
		paymentRequest.setReference(item);		
		// Sets your customer information.
		paymentRequest.setSender(u.getNome(), email, ddd, telefone);

		try {
			// Register this payment request in PagSeguro, to obtain the payment URL for redirect your customer.
			URL paymentURL = paymentRequest.register(this.getCredencialPagSeguro());
			return paymentURL.toString();
		} catch (PagSeguroServiceException e) {
			System.err.println(e.toString());
			return null;
		}
	}
	
	/*public static void main(String[] args) {
		
		new ManagerDAO(); //para criar a factory
		DoacaoDAO dDao = new DoacaoDAO();
		
		dDao.insert(new Doacao(new Usuarios("Renato", "0987654321"), 0.13f));
	}*/
}	
