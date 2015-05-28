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
import negocio.Pessoa.Sexos;
import negocio.Torcedor;
import negocio.Usuario;
import dao.EscolaSambaDAO;
import dao.TorcedorDAO;
import enumerator.Perfil;

/**
 * Servlet implementation class ControlaTorcedor
 */
public class ControlaTorcedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TorcedorDAO tabelaTorcedor = null;
	private HttpSession session = null;
	
	protected Torcedor recuperarTorcedorDaSession(HttpServletRequest request) {
	    	session = request.getSession();
			Usuario usuarioTorcedor = (Usuario) session.getAttribute("usuario");	
			tabelaTorcedor = new TorcedorDAO();
			Torcedor torcedor = (Torcedor)tabelaTorcedor.obterPorId(usuarioTorcedor.getId());
			return torcedor;
	}
   
    
    protected Torcedor criarObjeto(HttpServletRequest request)
    {    	
    	
    	Torcedor t = new Torcedor(request.getParameter("login").toString().trim(),request.getParameter("senha").toString());
		t.setNome( request.getParameter( "nome" ) );
		t.setEmail( request.getParameter( "email" ) );
		t.setTelefone( request.getParameter( "telefone" ) );
		t.setCpf( request.getParameter( "cpf" ) );
		t.setEndereco( request.getParameter( "endereco" ) );
		t.setCep( request.getParameter( "cep" ) );
		t.setSexo( Sexos.from( request.getParameter( "sexo" )));
		t.setPerfil(Perfil.TORCEDOR);
		t.setEscolaSamba( (EscolaSamba) new EscolaSambaDAO().obterPorIdEscola(Integer.valueOf(request.getParameter( "escolaSamba" ))));
		
		
    	return t;
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("tela") != null ){

			String tela = null;
			if(request.getParameter("tela") != null){
				tela = request.getParameter("tela");
			}else if(request.getAttribute("tela") != null){
				tela = (String) request.getAttribute("tela");
			}			
			
			switch (tela) {
			case "areaTorcedor":
				request.getRequestDispatcher("/areaTorcedor/index.jsp").forward(request, response);
				break;
			case "cadastrar":
												
				List<Entidade> listaEscolas = new EscolaSambaDAO().obterTodos();
				request.setAttribute("listaEscola", listaEscolas);
				
				request.getRequestDispatcher("indexTorcedor.jsp").forward(request, response);
				break;			
			case "escolas":			
				EscolaSamba escolaSamba =  (EscolaSamba) new EscolaSambaDAO().obterPorIdEscola(recuperarTorcedorDaSession(request).getEscolaSamba().getId());
								
				request.setAttribute("escolaSamba", escolaSamba);
				request.getRequestDispatcher("/areaTorcedor/minhasEscolas.jsp").forward(request, response);
				
				break;
			case "torcedores":							
				request.getRequestDispatcher("/areaTorcedor/torcedores.jsp").forward(request, response);
				
				break;
			}
			
			
		}else{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		tabelaTorcedor = new TorcedorDAO();
		
		switch (action) {
		
		case "cadastrar":
			
			if(tabelaTorcedor.cadastrar(this.criarObjeto(request))){
				request.setAttribute("logout", "Torcedor Cadastrado com sucesso!");
			}else{
				request.setAttribute("resultado", "Erro ao cadastrar o Torcedor!");
			}
			doGet(request, response);
			break;
		
		default:
			break;
		}
		
	}
}
