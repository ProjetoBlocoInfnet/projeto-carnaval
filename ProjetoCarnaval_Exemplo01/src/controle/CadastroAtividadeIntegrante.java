package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Atividade;
import negocio.EscolaSamba;
import negocio.Integrante;
import enumerator.Acao;
/**
 * Servlet implementation class CadastroAtividadeIntegrante
 */
public class CadastroAtividadeIntegrante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroAtividadeIntegrante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integrante integrante = IntegranteDAO.obtemPorId( request.getParameter("idIntegrante") );
		EscolaSamba escola = EscolaSambaDAO.obtemPeloNome( request.getParameter( "escola" ) );
		
		Atividade atividade = new Atividade();
		atividade.setAcao( Acao.from( request.getParameter( "atividade" )) );
		atividade.setEscolaSamba( escola );
		integrante.atividades.add( atividade );
		
		String id = AtividadeDAO.gravar( atividade );
		
	}

}
