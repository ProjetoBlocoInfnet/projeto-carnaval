package controle;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.EscolaSamba;
import dao.EscolaSambaDAO;

/**
 * Servlet implementation class ControlaEscolaSamba
 */
public class ControlaEscolaSamba extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlaEscolaSamba() {
        super();
        // TODO Auto-generated constructor stub
    }
    private EscolaSambaDAO tabelaEscolaSamba = new EscolaSambaDAO();

    protected boolean mantemEscolaSamba(String acao, HttpServletRequest request)
    {
		//TODO Emmanuel Aranha - Fazer as opções de salvar, alterar e excluir
    	
    	if("salvar".equals(acao))
    	{
    		EscolaSamba e = this.criarObjeto(request);
    		return tabelaEscolaSamba.inserir(e);
    	}
    	else if("consultar".equals(acao))
    	{
    		EscolaSamba e = tabelaEscolaSamba.obterPorNome(request.getParameter("nome").toString());
    		if(e != null)
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
    
    protected EscolaSamba criarObjeto(HttpServletRequest request)
    {
    	EscolaSamba e = new EscolaSamba(request.getParameter("login").toString(),request.getParameter("senha").toString());
    	e.setNome(request.getParameter("nome").toString());
    	e.getCor().add(request.getParameter("cor").toString());
    	e.setDataFundacao(new Date(Integer.valueOf(request.getParameter("dataFundacao"))));
    	e.setEmail(request.getParameter("email").toString());
    	e.setEnderecoBarracao(request.getParameter("enderecoBarracao").toString());
    	e.setEnderecoQuadra(request.getParameter("enderecoQuadra").toString());
    	e.setFiliacao(request.getParameter("filiacao").toString());
    	//e.setGrupoAtual(grupoAtual);
    	e.setLema(request.getParameter("lema").toString());
    	e.setTelefone(request.getParameter("nome").toString());
    	return e;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Emmanuel Aranha - Salva e continua na tela de manter
		if(this.mantemEscolaSamba(request.getParameter("acao").toString(), request))
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
		//TODO Emmanuel Aranha - Salva e sai da tela de manter
	}

}
