package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Integrante;
import negocio.Pessoa.Sexos;

/**
 * Servlet implementation class CadastroIntegrante
 */
public class CadastroIntegrante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integrante integrante = new Integrante( request.getParameter( "login" ), request.getParameter( "senha" ) );
		
		integrante.setNome( request.getParameter( "nome" ) );
		integrante.setEmail( request.getParameter( "email" ) );
		integrante.setTelefone( request.getParameter( "telefone" ) );
		integrante.setCpf( request.getParameter( "cpf" ) );
		integrante.setEndereco( request.getParameter( "endereco" ) );
		integrante.setCep( request.getParameter( "cep" ) );
		integrante.setSexo( Sexos.from( request.getParameter( "sexo" ) ) );
		
		String id = IntegranteDAO.gravar( integrante );		
	}

}
