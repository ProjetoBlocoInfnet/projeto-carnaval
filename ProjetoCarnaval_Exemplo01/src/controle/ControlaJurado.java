package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Entidade;
import negocio.Jurado;
import negocio.Pessoa.Sexos;
import negocio.Quesito;
import dao.JuradoDAO;
import dao.QuesitoDAO;

/**
 * Servlet implementation class ControlaJurado
 */
public class ControlaJurado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlaJurado() {
        super();
        // TODO Auto-generated constructor stub
    }

    private JuradoDAO tabelaJurados = new JuradoDAO();

 

    protected Jurado criarObjeto(HttpServletRequest request)
    {	
    	String login = request.getParameter("nome").trim();
    	login = login.replace(" ", "");
    	String senha = "123456";
    	
    	Jurado j = new Jurado(login,senha);
    	
    	j.setNome(request.getParameter("nome").toString());
    	j.setEndereco(request.getParameter("endereco").toString());
    	j.setSexo(Sexos.from(request.getParameter("sexo")));
    	j.setTelefone(request.getParameter("telefone").toString());
    	j.setCep(request.getParameter("cep").toString());
    	j.setCpf(request.getParameter("cpf").toString());
    	j.setEmail(request.getParameter("email").toString());    	
    	j.setQuesitoJulgado(new Quesito(Integer.valueOf(request.getParameter("quesito"))));
    	return j;
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Entidade> listaJurados = tabelaJurados.obterTodos();
		request.setAttribute("listaJurado", listaJurados);
			
		request.getRequestDispatcher("/jurado/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String action = request.getParameter("action");
		
		switch (action) {
		case "telaCadastro":
			
			QuesitoDAO q = new QuesitoDAO();
			List<Entidade> listaQuesitos = (List<Entidade>) q.obterTodos();
			request.setAttribute("listaQuesitos", listaQuesitos);
			request.getRequestDispatcher("/jurado/CadastroJurado.jsp").forward(request, response);
			break;
		case "cadastrar":
			
			if(tabelaJurados.cadastrar(this.criarObjeto(request))){
				request.setAttribute("resultado_ok", "Jurado Cadastrado com sucesso!");
			}else{
				request.setAttribute("resultado_error", "Erro ao cadastrar o Jurado!");
			}
			doGet(request, response);
			break;	
			
		case "consultar":
			if(request.getParameter("nome") !=null){
				
				List<Entidade> listaJurados  = tabelaJurados.obterListaPorNome(request.getParameter("nome").toString());
				if(listaJurados.size()> 0){
					
					request.setAttribute("listaJurado", listaJurados);
					request.getRequestDispatcher("/jurado/index.jsp").forward(request, response);
				}else{
					request.setAttribute("resultado_error", "Nenhum jurado foi encontrado");
					doGet(request, response);
				}
			
			}else{
				request.setAttribute("resultado_error", "Por favor, digite o jurado a ser consultado");
				doGet(request, response);
			}
			
			break;
		default:
			break;
		}
	}

}