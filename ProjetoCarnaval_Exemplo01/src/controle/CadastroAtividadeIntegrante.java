package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AtividadeDAO;
import dao.EscolaSambaDAO;
import dao.IntegranteDAO;
import negocio.Atividade;
import negocio.EscolaSamba;
import negocio.Integrante;
import negocio.Acao;
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
		//Integrante integrante = (Integrante) new IntegranteDAO().obterPorId( Integer.valueOf(request.getParameter("idIntegrante")) );
		EscolaSamba escola = new EscolaSambaDAO().obterPorNome( request.getParameter( "escola" ) );

		Atividade atividade = new Atividade();
		//atividade.setAcao( Acao.from( request.getParameter( "atividade" )) );
		Acao a = new Acao();
		a.setId(Integer.valueOf(request.getParameter("idIntegrante")));
		atividade.setAcao(a);
		atividade.setEscolaSamba( escola );
		atividade.setId_integrante(Integer.valueOf(request.getParameter("idIntegrante")));;

		new AtividadeDAO().cadastrar( atividade );
	}

}
