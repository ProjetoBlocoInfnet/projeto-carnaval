/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-04-30 18:45:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.ensaio;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import negocio.Usuario;
import negocio.Entidade;
import negocio.Ensaio;
import negocio.EscolaSamba;
import enumerator.Grupos;
import java.util.List;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html >\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Projeto Carnaval</title>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\t\n");
      out.write("\t<link href=\"http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css\" rel=\"stylesheet\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" \n");
      out.write("                  href=\"http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css\">\n");
      out.write("\t\n");
      out.write("\t<script type=\"text/javascript\"\n");
      out.write("         src=\"http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js\">\n");
      out.write("        </script> \n");
      out.write("        <script type=\"text/javascript\"\n");
      out.write("         src=\"http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js\">\n");
      out.write("        </script>\n");
      out.write("        <script type=\"text/javascript\"\n");
      out.write("         src=\"http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js\">\n");
      out.write("        </script>\n");
      out.write("        <script type=\"text/javascript\"\n");
      out.write("         src=\"http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js\">\n");
      out.write("        </script>\n");
      out.write("\t\t\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");

Usuario usuario = (Usuario) session.getAttribute("usuario");

List<Entidade> listaEnsaios = null;
if(request.getAttribute("listaEnsaio") != null){
	listaEnsaios = (List<Entidade>) request.getAttribute("listaEnsaio");
}

      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t\n");
      out.write("\t<div>\n");
      out.write("\t<h1>Área da Escola de Samba</h1>\n");
      out.write("\t\n");
      out.write("\t<form action=\"Logout\" method=\"post\">\n");
      out.write("\t\t  <div class=\"form-group\">\n");
      out.write("\t\t      <button type=\"submit\" class=\"btn btn-primary\">Logout</button>\n");
      out.write("\t\t  </div>\n");
      out.write("\t</form>\n");
      out.write("\t<div class=\"alert alert-info\" role=\"alert\">\n");
      out.write("\t<strong>Usuário: </strong>");
      out.print(usuario.getLogin() );
      out.write("<br>\n");
      out.write("\t<strong>Perfil: </strong>");
      out.print(usuario.getPerfil().nomeBonito );
      out.write("<br>\n");
      out.write("\t</div>\t\n");
      out.write("\t<hr/>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t<form action=\"ControlaEnsaio\" method=\"post\">\n");
      out.write("\t\t<input type=\"hidden\" name=\"action\" value=\"telaCadastro\">\n");
      out.write("\t\t<a href=\"indexEscolaSamba.jsp\"><button type=\"button\" class=\"btn btn-default\">Voltar</button></a>\n");
      out.write("\t\t<input type=\"submit\" class=\"btn btn-info\" value=\"Cadastrar Ensaio\">\n");
      out.write("\t</form>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t<hr>\n");
      out.write("\t<form action=\"ControlaEnsaio\" method=\"post\">\n");
      out.write("\t\t\n");
      out.write("\t\t<input type=\"hidden\" name=\"action\" value=\"consultar\">\n");
      out.write("\t\t\n");
      out.write("\t\t<div class=\"control-group\">\n");
      out.write("\t\t  <label class=\"control-label\" for=\"datetimepicker\"></label>\n");
      out.write("\t\t  <div class=\"controls\">\n");
      out.write("\t\t    <div id=\"datetimepicker\" class=\"input-prepend date\">\n");
      out.write("\t\t   <!--  <div id=\"datetimepicker\" class=\"input-append date\">-->\n");
      out.write("\t\t      <span class=\"add-on\"><i data-time-icon=\"icon-time\" data-date-icon=\"icon-calendar\"></i></span>\n");
      out.write("\t\t      <input name=\"data\" class=\"span2\" id=\"datetimepicker\" type=\"text\" placeholder=\"Consultar por data\">\t\t      \t\t    \n");
      out.write("\t\t    </div>\n");
      out.write("\t\t  </div>\n");
      out.write("\t\t   <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("\t\t      <button type=\"submit\" class=\"btn btn-default\">Consultar</button>\n");
      out.write("\t\t    </div>\n");
      out.write("\t\t</div>\t        \n");
      out.write("        \n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("          $('#datetimepicker').datetimepicker({\n");
      out.write("            format: 'dd/MM/yyyy hh:mm:ss',\n");
      out.write("            language: 'pt-BR'\n");
      out.write("          });\n");
      out.write("        </script>\n");
      out.write("        \n");
      out.write("\t\t       \n");
      out.write("\t</form>\n");
      out.write("\t\n");
      out.write("\t<hr>\n");
      out.write("\t\n");
      out.write("\t");
 if(listaEnsaios.size() > 0 ){ 
      out.write("\n");
      out.write("\t<div >\n");
      out.write("\t<table class=\"table table-hover\">\n");
      out.write("  \t\t<thead>\n");
      out.write("  \t\t<th>Id</th>\n");
      out.write("  \t\t<th>Data </th>\n");
      out.write("  \t\t<th>Escola</th>\n");
      out.write("  \t\t<th>Ação</th>\n");
      out.write("  \t\t</thead>\n");
      out.write("  \t\t<tbody>\n");
      out.write("  \t\t");
 for(Entidade entidade : listaEnsaios){ 
  			Ensaio ensaio = (Ensaio) entidade; 
  		
      out.write("\n");
      out.write("\t  \t\t<tr>\n");
      out.write("\t  \t\t\t<td>");
      out.print(ensaio.getId() );
      out.write("</td>  \n");
      out.write("\t  \t\t\t<td>");
      out.print(ensaio.getData() );
      out.write("</td>\n");
      out.write("\t  \t\t\t<td>");
      out.print(ensaio.getEscola().getNome() );
      out.write("</td>  \n");
      out.write("\t  \t\t\t<td>\n");
      out.write("\t  \t\t\t\t<a href=\"ControlaEnsaio?idEnsaio=");
      out.print(ensaio.getId());
      out.write("&acao=alterar\"><i class=\"icon-pencil\"></i></a> | \n");
      out.write("\t  \t\t\t\t<a href=\"ControlaEnsaio?idEnsaio=");
      out.print(ensaio.getId());
      out.write("&acao=excluir\"><i class=\"icon-trash\"></i></a>\n");
      out.write("\t  \t\t\t</td>\n");
      out.write("\t  \t\t</tr>\n");
      out.write("  \t\t");
 } 
      out.write("\n");
      out.write("  \t\t</tbody>\n");
      out.write("\t</table>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t");
 } else{ 
      out.write("\n");
      out.write("\t\n");
      out.write("\t");
 } 
      out.write("\n");
      out.write("\t\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}