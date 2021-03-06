package controle;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Entidade;
import negocio.EscolaSamba;
import dao.EscolaSambaDAO;
import enumerator.Grupos;


public class ControlaEscolaSamba extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ControlaEscolaSamba() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private EscolaSambaDAO tabelaEscolaSamba = new EscolaSambaDAO();


  
    protected EscolaSamba criarObjeto(HttpServletRequest request)
    {
    	String login = request.getParameter("nome").trim();
    	login = login.replace(" ", "");
    	String senha = "123456";
    	
    	System.out.println(request.getParameter("dataFundacao"));
    	
    	EscolaSamba e = new EscolaSamba(login.toLowerCase(), senha);
    	e.setNome(request.getParameter("nome").toString());
    	e.getCor().add(request.getParameter("cor").toString());
    	e.setDataFundacao(new Date(request.getParameter("dataFundacao")));
    	//e.setDataFundacao((Integer.valueOf(request.getParameter("dataFundacao"))));
    	e.setEmail(request.getParameter("email").toString());
    	e.setEnderecoBarracao(request.getParameter("enderecoBarracao").toString());
    	e.setEnderecoQuadra(request.getParameter("enderecoQuadra").toString());
    	e.setFiliacao(request.getParameter("filiacao").toString());
    	e.setGrupoAtual(Grupos.from(Integer.valueOf(request.getParameter("grupoAtual")))); 
    	e.setLema(request.getParameter("lema").toString());
    	e.setTelefone(request.getParameter("telefone").toString());
    	e.setCnpj(request.getParameter("cnpj"));
    	return e;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("acao") != null){
			
			String action = request.getParameter("acao");
			Integer id = Integer.valueOf(request.getParameter("idEscola")); 
			
			Entidade entidade = tabelaEscolaSamba.obterPorId(id);
			
			switch (action) {
			case "alterar":
				
				break;
			case "excluir":
				if(tabelaEscolaSamba.excluir(entidade)){
					request.setAttribute("resultado_ok", "Escola de Samba excluída com sucesso!");
				}else{
					request.setAttribute("resultado_error", "Erro ao excluir a Escola de Samba!");
				}
				break;
			default:
				break;
			}
			
			
		}
		List<Entidade> listaEscolas = tabelaEscolaSamba.obterTodos();
		request.setAttribute("listaEscola", listaEscolas);
		
		request.getRequestDispatcher("/escolaSamba/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		
		switch (action) {
		case "telaCadastro":
			request.getRequestDispatcher("/escolaSamba/CadastroEscolaSamba.jsp").forward(request, response);
			break;
		case "cadastrar":
			
			if(tabelaEscolaSamba.cadastrar(this.criarObjeto(request))){
				request.setAttribute("resultado_ok", "Escola de Samba Cadastrada com sucesso!");
			}else{
				request.setAttribute("resultado_error", "Erro ao cadastrar a Escola de Samba!");
			}
			doGet(request, response);
			break;
		case "consultar":
			if(request.getParameter("nome") !=null){
				
				List<Entidade> listaEscolas  = tabelaEscolaSamba.obterListaPorNome(request.getParameter("nome").toString());
				if(listaEscolas.size()> 0){
					
					request.setAttribute("listaEscola", listaEscolas);
					request.getRequestDispatcher("/escolaSamba/index.jsp").forward(request, response);
				}else{
					request.setAttribute("resultado_error", "Nenhuma com nome de "+ request.getParameter("nome") +"Escola foi encontrada");
					doGet(request, response);
				}
			
			}else{
				request.setAttribute("resultado_error", "Por favor, digite o nome da escola a ser consultada");
				doGet(request, response);
			}
			
			break;
		default:
			break;
		}
		
		

	}

}
