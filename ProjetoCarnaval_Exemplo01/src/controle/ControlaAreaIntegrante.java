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

import negocio.Ensaio;
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
    //private EscolaSambaDAO tabelaEscolaSamba = new EscolaSambaDAO();
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
    
    protected Set<EscolaSamba> setEscolaSamba(HttpServletRequest request) {
    	Integrante integrante = (Integrante) tabelaIntegrante.obterPorIdUsuario(this.obterUsuarioSession(request).getId());
		Set<EscolaSamba> listaEscola = integrante.getEscolaSamba();	
		
		//Set<EscolaSamba> listaEscola = tabelaIntegrante.obterPorId(this.obterUsuarioSession(request).getId());
		
		return listaEscola;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Códigos comentados podem ser utilizados é só ajustar
		
		
		if(request.getParameter("tela") != null || request.getAttribute("tela") != null){
			
			System.out.println(request.getAttribute("tela"));
			String tela = null;
			if(request.getParameter("tela") != null){
				tela = request.getParameter("tela");
			}else if(request.getAttribute("tela") != null){
				tela = (String) request.getAttribute("tela");
			}
			
			switch (tela) {
			
			case "escolas":			
				
				request.setAttribute("listaEscola", this.setEscolaSamba(request));			
				request.getRequestDispatcher("/areaIntegrante/minhasEscolas.jsp").forward(request, response);
				
				break;
			case "torcedores":			
				
				request.setAttribute("listaEscolaConsulta", this.setEscolaSamba(request));		
				request.getRequestDispatcher("/areaIntegrante/torcedores.jsp").forward(request, response);
				
				break;
			
			case "ensaios":
				
				List<Ensaio> listaTodosEnsaio = new ArrayList<Ensaio>();
				List<Ensaio> listaEnsaios = null;
				
				for (EscolaSamba escolaSamba : this.setEscolaSamba(request)) {					
					listaEnsaios = (List<Ensaio>) tabelaEnsaio.obterTodos(escolaSamba);					
					for (Ensaio ensaio : listaEnsaios) {
						listaTodosEnsaio.add(ensaio);
					}					
				}
				request.setAttribute("listaEnsaio", listaTodosEnsaio);
				//request.setAttribute("listaEscolaConsulta", this.setEscolaSamba(request));
				//request.setAttribute("listaEnsaio", listaTodosEnsaio);
				
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
				request.setAttribute("tela","ensaios");
				doGet(request, response);
			}
			
			break;
		case "consultarEnsaioEscola":
			
			if(request.getParameter("escolaId") != null){
				
					System.out.println(request.getParameter("escolaId"));
					
					Integrante integrante = (Integrante) tabelaIntegrante.obterPorId(this.obterUsuarioSession(request).getId());
					Set<EscolaSamba> SetEscola = integrante.getEscolaSamba();		
					List<Ensaio> listEnsaio = new ArrayList<Ensaio>();
					
					Integer id = Integer.valueOf(request.getParameter("escolaId"));
					
					for (EscolaSamba escolaSamba : SetEscola) {
						if(escolaSamba.getId() == id){							
							 listEnsaio = (List<Ensaio>)tabelaEnsaio.obterTodos(escolaSamba);							 
						}
					}
					
					for (Ensaio ensaio : listEnsaio) {
						System.out.println(ensaio.getEscola().getNome());
						System.out.println(ensaio.getData());
					}
										
					if(listEnsaio.size()> 0){
						System.out.println("entrou aqui");
						request.setAttribute("listaEscolaConsulta", this.setEscolaSamba(request));
						request.setAttribute("listaEnsaio", listEnsaio);
						request.getRequestDispatcher("/areaIntegrante/ensaios.jsp").forward(request, response);
					}else{
						request.setAttribute("tela","ensaios");
						request.setAttribute("resultado_error", "Nenhum Ensaio foi encontrado");
						doGet(request, response);
					}
						
				}else{
					request.setAttribute("tela","ensaios");
					doGet(request, response);
				}
			
			break;
		default:
			break;
		}
	
	}

}
