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

   
    protected Quesito criarObjeto(HttpServletRequest request)
    {
    	Quesito q = new Quesito();
    	q.setNome(request.getParameter("nome").toString());
    	q.setDescricao(request.getParameter("descricao").toString());
    	return q;
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("acao") != null){
			
			String action = request.getParameter("acao");
			Integer id = Integer.valueOf(request.getParameter("idQuesito")); 
			
			Entidade entidade = tabelaQuesitos.obterPorId(id);
			
			switch (action) {
			case "alterar":
				
				break;
			case "excluir":
				if(tabelaQuesitos.excluir(entidade)){
					request.setAttribute("resultado_ok", "Quesito excluído com sucesso!");
				}else{
					request.setAttribute("resultado_error", "Erro ao excluir o Quesito!");
				}
				break;
			default:
				break;
			}
			
			
		}
				
		List<Entidade> listaQuesitos = tabelaQuesitos.obterTodos();
		request.setAttribute("listaQuesito", listaQuesitos);
	
		request.getRequestDispatcher("/quesito/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		String action = request.getParameter("action");
		
		switch (action) {
		case "telaCadastro":
			request.getRequestDispatcher("/quesito/CadastroQuesito.jsp").forward(request, response);
			break;
		case "cadastrar":
			
			if(tabelaQuesitos.cadastrar(this.criarObjeto(request))){
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
