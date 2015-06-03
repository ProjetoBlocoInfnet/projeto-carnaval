package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Ensaio;
import negocio.EscolaSamba;
import dao.EscolaSambaDAO;
import dao.TorcedorDAO;


public class ControlaCarnaval extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("acao") != null || request.getAttribute("acao") != null){
			
			
			String acao = request.getParameter("acao");
			
			switch (acao) {
			
			case "quesito":			
				
				request.getRequestDispatcher("/carnaval/carnavalQuesito.jsp").forward(request, response);
				
				break;
			case "jurado":			
				request.getRequestDispatcher("/carnaval/carnavalJurado.jsp").forward(request, response);
				break;
			}
			
		}else{
			request.getRequestDispatcher("/carnaval/index.jsp").forward(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "telaCadastro":
			
			request.getRequestDispatcher("/carnaval/CadastroCarnaval.jsp").forward(request, response);
			break;

		default:
			break;
		}
		
	}

}
