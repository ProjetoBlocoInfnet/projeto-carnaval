/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.59
 * Generated at: 2015-05-14 02:18:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.areaTorcedor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import negocio.Usuario;
import negocio.Entidade;
import negocio.EscolaSamba;
import enumerator.Grupos;
import java.util.Set;

public final class minhasEscolas_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Projeto Carnaval</title>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\t<!-- Latest compiled and minified CSS -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\">\n");
      out.write("\t\n");
      out.write("\t<!-- Optional theme -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css\">\n");
      out.write("\t\n");
      out.write("\t<!-- Latest compiled and minified JavaScript -->\n");
      out.write("\t<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>\n");
      out.write("\t<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js\"></script>\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js\"></script>\n");
      out.write("\t\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t\n");
      out.write("\t<div>\n");
      out.write("\t<h1>Área do Torcedor</h1>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t<hr/>\n");
      out.write("\t<a href=\"ControlaTorcedor\"><button type=\"button\" class=\"btn btn-default\">Voltar</button></a>\n");
      out.write("\t<br><br>\n");
      out.write("\t\t\n");
      out.write("\t<form action=\"ControlaTorcedor\" method=\"post\">\n");
      out.write("\t\t<input type=\"hidden\" name=\"action\" value=\"consultarEscola\">\n");
      out.write("\t\t<div class=\"row\">  \n");
      out.write("\t\t  <div class=\"col-lg-6\">\n");
      out.write("\t\t    <div class=\"input-group\">\n");
      out.write("\t\t      <input type=\"text\" name=\"nome\" class=\"form-control\" placeholder=\"Consultar por...\">\n");
      out.write("\t\t      <span class=\"input-group-btn\">\n");
      out.write("\t\t        <input type=\"submit\" class=\"btn btn-default\" type=\"button\" value=\"Consultar\">\n");
      out.write("\t\t      </span>\n");
      out.write("\t\t    </div><!-- /input-group -->\n");
      out.write("\t\t  </div><!-- /.col-lg-6 -->\n");
      out.write("\t\t</div><!-- /.row -->\n");
      out.write("\t</form>\n");
      out.write("\t<hr>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t<div >\n");
      out.write("\t<h4>Lista de minhas Escolas de Samba</h4>\n");
      out.write("\t<hr>\n");
      out.write("\t<table class=\"table table-hover\">\n");
      out.write("  \t\t<thead>\n");
      out.write("  \t\t<th>Id</th>\n");
      out.write("  \t\t<th>Nome Escola\t</th>\n");
      out.write("  \t\t<th>Endereço Quadra</th>\n");
      out.write("  \t\t<th>Endereço Barracão</th>\n");
      out.write("  \t\t<th>Telefone</th>\n");
      out.write("  \t\t<th>E-mail</th>\n");
      out.write("  \t\t<th>CNPJ</th>\n");
      out.write("  \t\t<th>Grupo</th>\n");
      out.write("  \t\t</thead>\n");
      out.write("  \t\t\n");
      out.write("\t  \t\t<tr>\n");
      out.write("\t  \t\t\t\t\n");
      out.write("\t  \t\t\t\n");
      out.write("\t  \t\t</tr>\n");
      out.write("  \t\t\n");
      out.write("  \t\t</tbody>\n");
      out.write("\t</table>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
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
