package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Entidade;
import negocio.EscolaSamba;
import negocio.Integrante;
import negocio.Usuario;
import dao.EnsaioDAO;
import dao.EscolaSambaDAO;
import dao.IntegranteDAO;

/**
 * Servlet implementation class ControlaAreaIntegrante
 */
public class ControlaAreaIntegrante extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private HttpSession session = null;
	
	private EnsaioDAO tabelaEnsaio = new EnsaioDAO();
    private EscolaSambaDAO tabelaEscolaSamba = new EscolaSambaDAO();
    private IntegranteDAO tabelaIntegrante = new IntegranteDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlaAreaIntegrante() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected Usuario obterUsuarioSession(HttpServletRequest request){
    	session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return usuario;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("tela") != null){
			
			String tela = request.getParameter("tela");
			Integrante integrante = (Integrante) tabelaIntegrante.obterPorId(this.obterUsuarioSession(request).getId());
			Set<EscolaSamba> listaEscola = integrante.getEscolaSamba();		
			
			switch (tela) {
			
			case "escolas":			
							
				for (EscolaSamba escolaSamba : listaEscola) {
					System.out.println(escolaSamba.getNome());
				}
				
				request.setAttribute("listaEscola", listaEscola);			
				request.getRequestDispatcher("/areaIntegrante/minhasEscolas.jsp").forward(request, response);
				
				break;
			
			case "ensaios":
								
				
				for (EscolaSamba escolaSamba : listaEscola) {
					System.out.println(escolaSamba.getNome());
				}
				
				request.setAttribute("listaEscolaConsulta", listaEscola);
				
				request.getRequestDispatcher("/areaIntegrante/ensaios.jsp").forward(request, response);
				
				break;	
				
			default:
				break;
			}
		}else{
			request.getRequestDispatcher("/indexIntegrante.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		switch (action) {
		case "consultarEscola":
			
			if(request.getParameter("nome") !=null){
			
					Integrante integrante = (Integrante) tabelaIntegrante.obterPorId(this.obterUsuarioSession(request).getId());
					Set<EscolaSamba> SetEscola = integrante.getEscolaSamba();		
					Set<EscolaSamba> listEscola = new HashSet<EscolaSamba>();
					
					String nome = request.getParameter("nome");
					
					for (EscolaSamba escolaSamba : SetEscola) {
						if(escolaSamba.getNome().toLowerCase().contains(nome.toLowerCase())){
							listEscola.add(escolaSamba);
						}
					}
					if(listEscola.size()> 0){
						
						request.setAttribute("listaEscola", listEscola);
						request.getRequestDispatcher("/areaIntegrante/minhasEscolas.jsp").forward(request, response);
					}else{
						request.setAttribute("resultado_error", "Nenhuma Escola com nome de "+ request.getParameter("nome") +"foi encontrada");
						doGet(request, response);
					}
							
			}else{
				doGet(request, response);
			}
			
			break;
		case "consultarEnsaioEscola":
			
			if(request.getParameter("idEscola") !=null){
				
				Integrante integrante = (Integrante) tabelaIntegrante.obterPorId(this.obterUsuarioSession(request).getId());
				Set<EscolaSamba> SetEscola = integrante.getEscolaSamba();		
				List<Entidade> listEnsaio = new ArrayList<Entidade>();
				
				Integer id = Integer.valueOf(request.getParameter("idEscola"));
				
				for (EscolaSamba escolaSamba : SetEscola) {
					if(escolaSamba.getId() == id){
						
						 listEnsaio = (List<Entidade>)tabelaEnsaio.obterTodos(escolaSamba);
						
					}
				}
				if(listEnsaio.size()> 0){
					
					request.setAttribute("listaEnsaio", listEnsaio);
					request.getRequestDispatcher("/areaIntegrante/ensaios.jsp").forward(request, response);
				}else{
					request.setAttribute("resultado_error", "Nenhum Ensaio foi encontrado");
					doGet(request, response);
				}
						
		}else{
			doGet(request, response);
		}
			
			break;
		default:
			break;
		}
	
	}

}