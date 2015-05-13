package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Entidade;
import negocio.EscolaSamba;
import negocio.Integrante;
import negocio.Pessoa.Sexos;
import negocio.Usuario;
import dao.EscolaSambaDAO;
import dao.IntegranteDAO;

/**
 * Servlet implementation class CadastroIntegrante
 */
public class ControlaIntegrante extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpSession session = null;
      
	 private IntegranteDAO tabelaItegrante = new IntegranteDAO();
	 
	 protected EscolaSamba recuperarEscolaDaSession(HttpServletRequest request) {
	    	session = request.getSession();
			Usuario usuarioEscola = (Usuario) session.getAttribute("usuario");			
			EscolaSambaDAO DAOEscola = new EscolaSambaDAO();
			EscolaSamba escola = (EscolaSamba)DAOEscola.obterPorId(usuarioEscola.getId());
			return escola;
	}
	    
	protected Integrante criarObjeto(HttpServletRequest request)
	    {
	    	String login = request.getParameter("nome").trim();
	    	login = login.replace(" ", "");
	    	String senha = "123456";
	    	
	    	Integrante integrante = new Integrante( login, senha );
	    	
			integrante.setNome( request.getParameter( "nome" ) );
			integrante.setEmail( request.getParameter( "email" ) );
			integrante.setTelefone( request.getParameter( "telefone" ) );
			integrante.setCpf( request.getParameter( "cpf" ) );
			integrante.setEndereco( request.getParameter( "endereco" ) );
			integrante.setCep( request.getParameter( "cep" ) );
			integrante.setSexo( Sexos.from( request.getParameter( "sexo" ) ) );
								
			integrante.setEscolaSamba(this.recuperarEscolaDaSession(request));	  

	    	return integrante;
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("acao") != null){
			
			String action = request.getParameter("acao");
			Integer id = Integer.valueOf(request.getParameter("idIntegrante")); 
			
			Entidade entidade = tabelaItegrante.obterPorId(id);
			
			switch (action) {
			case "alterar":
				
				break;
			case "excluir":
				if(tabelaItegrante.excluir(entidade)){
					request.setAttribute("resultado_ok", "Integrante excluído com sucesso!");
				}else{
					request.setAttribute("resultado_error", "Erro ao excluir o Integrante!");
				}
				break;
			default:
				break;
			}
			
			
		}
		List<Entidade> listaIntegrante = tabelaItegrante.obterTodos();
		request.setAttribute("listaIntegrante", listaIntegrante);
		
		request.getRequestDispatcher("/integrante/index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		
		switch (action) {
		case "telaCadastro":
			request.getRequestDispatcher("/integrante/CadastroIntegrante.jsp").forward(request, response);
			break;
		case "cadastrar":
			
			if(tabelaItegrante.cadastrar(this.criarObjeto(request))){
				request.setAttribute("resultado_ok", "Integrante Cadastrado com sucesso!");
			}else{
				request.setAttribute("resultado_error", "Erro ao cadastrar o Integrante!");
			}
			doGet(request, response);
			break;
		case "consultar":
			if(request.getParameter("nome") !=null){
				
				//verificar
				List<Entidade> listaIntegrantes  = tabelaItegrante.obterListaPorNome(request.getParameter("nome").toString(),this.recuperarEscolaDaSession(request));
				//List<Entidade> listaIntegrantes  = tabelaItegrante.obterListaPorNome(request.getParameter("nome").toString());
				if(listaIntegrantes.size()> 0){
					
					request.setAttribute("listaIntegrante", listaIntegrantes);
					request.getRequestDispatcher("/integrante/index.jsp").forward(request, response);
				}else{
					request.setAttribute("resultado_error", "Nenhum Itegrante com nome de "+ request.getParameter("nome") +" foi encontrada");
					doGet(request, response);
				}
			
			}else{
				request.setAttribute("resultado_error", "Por favor, digite o nome do Integrante a ser consultado");
				doGet(request, response);
			}
			
			break;
		default:
			break;
		}
	}

}
