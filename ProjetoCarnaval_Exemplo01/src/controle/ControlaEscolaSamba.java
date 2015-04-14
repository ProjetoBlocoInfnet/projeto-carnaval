package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    protected boolean mantemEscolaSamba(String acao, HttpServletRequest request)
    {
		//TODO Emmanuel Aranha - Fazer as opções de salvar, alterar e excluir
    	if("Salvar".equals(acao))
    	{
    		return true;
    	}
		return false;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Emmanuel Aranha - Salva e continua na tela de manter
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Emmanuel Aranha - Salva e sai da tela de manter
	}

}
