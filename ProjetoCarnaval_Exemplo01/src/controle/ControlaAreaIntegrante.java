package controle;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			switch (tela) {
			case "escolas":
				
							
				Integrante integrante = (Integrante) tabelaIntegrante.obterPorId(this.obterUsuarioSession(request).getId());
				
				Set<EscolaSamba> listaEscola = integrante.getEscolaSamba();						
							
				for (EscolaSamba escolaSamba : listaEscola) {
					System.out.println(escolaSamba.getNome());
				}
				
				request.setAttribute("listaEscola", listaEscola);			
				request.getRequestDispatcher("/areaIntegrante/minhasEscolas.jsp").forward(request, response);
				
				break;
			
			case "ensaios":
				
							
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
					for (EscolaSamba escolaSamba : SetEscola) {
						if(escolaSamba.getNome().equalsIgnoreCase(request.getParameter("nome"))){
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

		default:
			break;
		}
	
	}

}
