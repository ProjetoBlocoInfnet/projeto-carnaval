package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EscolaSambaDAO;
import dao.TorcedorDAO;
import enumerator.Perfil;
import negocio.Ensaio;
import negocio.EscolaSamba;
import negocio.Torcedor;
import negocio.Pessoa.Sexos;

/**
 * Servlet implementation class ControlaTorcedor
 */
public class ControlaTorcedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlaTorcedor() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected boolean mantemTorcedor(String acao, HttpServletRequest request)
    {
    	if("salvar".equals(acao))
    	{
    		Torcedor t = this.criarObjeto(request);
    		return new TorcedorDAO().cadastrar(t);
    	}
    	else if("consultar".equals(acao))
    	{
    		Torcedor t = new TorcedorDAO().obterPorNome(request.getParameter("nome").toString());
    		if(t != null)
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
    
    protected Torcedor criarObjeto(HttpServletRequest request)
    {
    	Torcedor t = new Torcedor(request.getParameter("login").toString(),request.getParameter("senha").toString());
		t.setNome( request.getParameter( "nome" ) );
		t.setEmail( request.getParameter( "email" ) );
		t.setTelefone( request.getParameter( "telefone" ) );
		t.setCpf( request.getParameter( "cpf" ) );
		t.setEndereco( request.getParameter( "endereco" ) );
		t.setCep( request.getParameter( "cep" ) );
		t.setSexo( Sexos.from( request.getParameter( "sexos" ) ) );
		t.setPerfil(Perfil.TORCEDOR);
		t.setEscolaSamba(new EscolaSambaDAO().obterPorNome(request.getParameter( "escolaSamba" )));
    	return t;
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("tela") != null || request.getAttribute("tela") != null){
			
			System.out.println(request.getAttribute("tela"));
			String tela = null;
			if(request.getParameter("tela") != null){
				tela = request.getParameter("tela");
			}else if(request.getAttribute("tela") != null){
				tela = (String) request.getAttribute("tela");
			}
			
			switch (tela) {
			
			case "escolas":			
				
				
				request.getRequestDispatcher("/areaTorcedor/minhasEscolas.jsp").forward(request, response);
				
				break;
			case "torcedores":			
				
					
				request.getRequestDispatcher("/areaTorcedor/torcedores.jsp").forward(request, response);
				
				break;
			}
			
			
		}else{
			request.getRequestDispatcher("/indexTorcedor.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/indexTorcedor.jsp").forward(request, response);
	}
}
