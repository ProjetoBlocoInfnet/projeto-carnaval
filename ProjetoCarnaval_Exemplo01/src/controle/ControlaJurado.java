package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Jurado;
import dao.JuradoDAO;

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

    protected boolean mantemJurado(String acao, HttpServletRequest request)
    {
		//TODO Emmanuel Aranha - Fazer as opções de salvar, alterar e excluir
    	
    	if("salvar".equals(acao))
    	{
    		Jurado j = this.criarObjeto(request);
    		return tabelaJurados.cadastrar(j);
    	}
    	else if("consultar".equals(acao))
    	{
    		Jurado j = tabelaJurados.obterPorNome(request.getParameter("nome").toString());
    		if(j != null)
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

    protected Jurado criarObjeto(HttpServletRequest request)
    {
    	Jurado j = new Jurado(request.getParameter("login").toString(),request.getParameter("senha").toString());
    	
    	j.setNome(request.getParameter("nome").toString());
    	j.setEndereco(request.getParameter("endereco").toString());
    	//j.setSexo(sexo);
    	j.setTelefone(request.getParameter("telefone").toString());
    	j.setCep(request.getParameter("cep").toString());
    	j.setCpf(request.getParameter("cpf").toString());
    	j.setEmail(request.getParameter("email").toString());
    	return j;
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(this.mantemJurado(request.getParameter("acao").toString(), request))
		{
			request.setAttribute("oSucesso","s");
		}
		else
		{
			request.setAttribute("oSucesso","n");
		}
		request.setAttribute("oAcao",request.getParameter("acao").toString());
		request.getRequestDispatcher("CadastroQuesito.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
