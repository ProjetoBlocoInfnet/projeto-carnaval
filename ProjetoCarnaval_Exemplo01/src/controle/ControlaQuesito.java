package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//TODO Emmanuel Aranha - Fazer as opções de salvar, alterar e excluir
    	
    	if("salvar".equals(acao))
    	{
    		Quesito q = this.criarObjeto(request);
    		return tabelaQuesitos.inserir(q);
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
		// TODO Auto-generated method stub
		if(this.mantemQuesito(request.getParameter("acao").toString(), request))
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
