package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Entidade;
import negocio.Quesito;
import dao.QuesitoDAO;

/**
 * Servlet implementation class ControlaQuesito
 */
public class ControlaQuesito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlaQuesito() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private QuesitoDAO tabelaQuesitos = new QuesitoDAO();

    protected boolean mantemQuesito(String acao, HttpServletRequest request)
    {
		//TODO Emmanuel Aranha - Fazer as op��es de salvar, alterar e excluir
    	
    	if("salvar".equals(acao))
    	{
    		Quesito q = this.criarObjeto(request);
    		return tabelaQuesitos.cadastrar(q);
    	}
    	else if("consultar".equals(acao))
    	{
    		Quesito q = tabelaQuesitos.obterPorNome(request.getParameter("nome").toString());
    		if(q != null)
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
		return false;
    }

    protected Quesito criarObjeto(HttpServletRequest request)
    {
    	Quesito q = new Quesito();
    	q.setNome(request.getParameter("nome").toString());
    	q.setDescricao(request.getParameter("descricao").toString());
    	return q;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		List<Entidade> listaQuesitos = tabelaQuesitos.obterTodos();
		request.setAttribute("listaQuesito", listaQuesitos);
	
		request.getRequestDispatcher("/quesito/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
		if(this.mantemQuesito(request.getParameter("acao").toString(), request))
		{
			request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("oSucesso","n");
			request.setAttribute("oAcao",request.getParameter("acao").toString());
			request.getRequestDispatcher("CadastroQuesito.jsp").forward(request, response);
*/
	
		String action = request.getParameter("action");
		
		switch (action) {
		case "telaCadastro":
			request.getRequestDispatcher("/quesito/CadastroQuesito.jsp").forward(request, response);
			break;
		case "cadastrar":
			if(mantemQuesito("salvar",request)){
				request.setAttribute("resultado_ok", "Quesito Cadastrado com sucesso!");
			}else{
				request.setAttribute("resultado_error", "Erro ao cadastrar o Quesito!");
			}
			doGet(request, response);
			break;
		case "consultar":
			if(request.getParameter("nome") !=null){
				
				List<Entidade> listaQuesitos  = tabelaQuesitos.obterListaPorNome(request.getParameter("nome").toString());
				if(listaQuesitos.size()> 0){
					
					request.setAttribute("listaQuesito", listaQuesitos);
					request.getRequestDispatcher("/quesito/index.jsp").forward(request, response);
				}else{
					request.setAttribute("resultado_error", "Nenhum quesito foi encontrado");
					doGet(request, response);
				}
			
			}else{
				request.setAttribute("resultado_error", "Por favor, digite o quesito a ser consultado");
				doGet(request, response);
			}
			
			break;
		default:
			break;
		}
	}

}
