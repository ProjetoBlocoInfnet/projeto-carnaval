package controle;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Ensaio;
import negocio.Entidade;
import negocio.EscolaSamba;
import negocio.Usuario;
import dao.EnsaioDAO;
import dao.EscolaSambaDAO;


/**
 * Servlet implementation class CadastroEnsaio
 */
public class ControlaEnsaio extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HttpSession session = null;
	private EnsaioDAO tabelaEnsaio = new EnsaioDAO();
	
    public ControlaEnsaio() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected EscolaSamba recuperarEscolaDaSession(HttpServletRequest request) {
    	session = request.getSession();
		Usuario usuarioEscola = (Usuario) session.getAttribute("usuario");			
		EscolaSambaDAO DAOEscola = new EscolaSambaDAO();
		EscolaSamba escola = (EscolaSamba)DAOEscola.obterPorId(usuarioEscola.getId());
		return escola;
	}
    
    protected Ensaio criarObjeto(HttpServletRequest request)
    {    	
    	Ensaio ensaio = new Ensaio();
    	ensaio.setEscola(this.recuperarEscolaDaSession(request));   	
    	Date data = new Date( request.getParameter("data"));   	
    	ensaio.setData(data);
    	
		return ensaio;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("acao") != null){
			
			String action = request.getParameter("acao");
			Integer id = Integer.valueOf(request.getParameter("idEnsaio")); 
			
			Entidade entidade = tabelaEnsaio.obterPorId(id);
			
			switch (action) {
			case "alterar":
				
				break;
			case "excluir":
				if(tabelaEnsaio.excluir(entidade)){
					request.setAttribute("resultado_ok", "Ensaio excluído com sucesso!");
				}else{
					request.setAttribute("resultado_error", "Erro ao excluir o Ensaio!");
				}
				break;
			default:
				break;
			}
			
			
		}
		List<Ensaio> listaEnsaio = tabelaEnsaio.obterTodos(this.recuperarEscolaDaSession(request));
		request.setAttribute("listaEnsaio", listaEnsaio);
		
		request.getRequestDispatcher("/ensaio/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
String action = request.getParameter("action");
		
		switch (action) {
		case "telaCadastro":
			request.getRequestDispatcher("/ensaio/CadastroEnsaio.jsp").forward(request, response);
			break;
		case "cadastrar":
			
			if(tabelaEnsaio.cadastrar(this.criarObjeto(request))){
				request.setAttribute("resultado_ok", "Ensaio Cadastrado com sucesso!");
			}else{
				request.setAttribute("resultado_error", "Erro ao cadastrar o Ensaio!");
			}
			doGet(request, response);
			break;
		case "consultar":
			if(request.getParameter("nome") !=null){
				Date data = new Date( request.getParameter("data"));				
				List<Entidade> listaEnsaios  = tabelaEnsaio.obterPorData(data,this.recuperarEscolaDaSession(request));
				if(listaEnsaios.size()> 0){
					
					request.setAttribute("listaEnsaio", listaEnsaios);
					request.getRequestDispatcher("/ensaio/index.jsp").forward(request, response);
				}else{
					request.setAttribute("resultado_error", "Nenhum Ensaio na data "+ request.getParameter("data") +" foi encontrado");
					doGet(request, response);
				}
			
			}else{
				request.setAttribute("resultado_error", "Por favor, digite a data do Ensaio a ser consultado");
				doGet(request, response);
			}
			
			break;
		default:
			break;
		}
		
	}

}
