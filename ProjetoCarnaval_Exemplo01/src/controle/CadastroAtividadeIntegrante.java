package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AtividadeDAO;
import dao.EscolaSambaDAO;
import negocio.Atividade;
import negocio.EscolaSamba;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Integrante integrante = (Integrante) new IntegranteDAO().obterPorId( Integer.valueOf(request.getParameter("idIntegrante")) );
		EscolaSamba escola = new EscolaSambaDAO().obterPorNome( request.getParameter( "escola" ) );

		Atividade atividade = new Atividade();
		//TODO mudou a forma de pegar a acao!!! Tem que usar o AcaoDAO pra montar a combo agora
		//TODO e colocar o id_acao como valor obtido da combo.
		//atividade.setAcao( Acao.from( request.getParameter( "atividade" )) );
//		Acao a = new Acao();
//		a.setId(Integer.valueOf(request.getParameter("atividade")));
//		atividade.setAcao(a);
		atividade.setEscolaSamba( escola );
		atividade.setId_integrante(Integer.valueOf(request.getParameter("idIntegrante")));;
		//TODO Falta a data inicio e data fim!!!
		new AtividadeDAO().cadastrar( atividade );
	}

}
