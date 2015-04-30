package controle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import negocio.Pessoa;
import negocio.Usuario;

@WebFilter(urlPatterns="/*")
public class FiltroDeAcesso implements Filter{
	
	 private FilterConfig context = null;  
	
	@Override
	public void init(FilterConfig config) throws ServletException {		
		this.context = config; 
       System.out.println("Filtro de Autenticação inicializado");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String loginURI = "/Login";
		String resource = req.getRequestURI().replaceAll(req.getContextPath(), "");
				       		 
	    Usuario usuario = getUsuario(req);
         
	    if(usuario == null && "/CadastroTorcedor.jsp".equals(resource)){ //Necessário para que o torcedor possa fazer seu cadastro
        	System.out.println("Possível torcedor acessando a URI "  + req.getRequestURI());
        	request.getRequestDispatcher("CadastroTorcedor.jsp").forward(request, response);
        }else if(usuario == null && "/ControlaTorcedor".equals(resource)){ //Necessário para que o torcedor possa fazer seu cadastro
        	System.out.println("Possível torcedor acessando a URI "  + req.getRequestURI());
        	request.getRequestDispatcher("ControlaTorcedor").forward(request, response);
        }else if(usuario == null && !resource.equals(loginURI)){
        	System.out.println("Usuario deslogado acessando a URI "  + req.getRequestURI());      	
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }else if(usuario == null && resource.equals(loginURI)){
        	System.out.println("Usuario deslogado acessando a URI "  + req.getRequestURI());
        	chain.doFilter(request, response);
        }else if(usuario != null){       	
        	System.out.println("Usuario " + usuario.getLogin() + " acessando a URI "  + req.getRequestURI());    
        	chain.doFilter(request, response);
        }
       
		        
	}
	

	private Usuario getUsuario(HttpServletRequest req) {
		
		 HttpSession session = req.getSession();		 
	     Usuario usuario = (Usuario) session.getAttribute("usuario");	     
        if (usuario == null)
            return null;
        else{
        	return usuario;
        }
             
    }
	
	@Override
	public void destroy() {
		this.context = null;
	}
	
}
