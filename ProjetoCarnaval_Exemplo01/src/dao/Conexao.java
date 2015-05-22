package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao 
{
	private Conexao(){
	}
	
	public static Connection getConexao(){
		Connection c = null;
		/*
		 String url = "jdbc:mysql://localhost/projeto_carnaval";  
	     String login = "root";  
	     String senha = "Debora0103";  
			*/
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				return DriverManager.getConnection("jdbc:mysql://localhost/projeto_carnaval","root","93956619");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            //return DriverManager.getConnection(url, login, senha);
			//return DriverManager.getConnection("jdbc:mysql://localhost/projeto_carnaval","root","93956619");
			
			//c = DriverManager.getConnection("jdbc:mysql://localhost/projeto_carnaval","root","Debora0103");
			//Ambiente dev Emmanuel
			//c = DriverManager.getConnection("jdbc:mysql://localhost/projeto_carnaval","root","93956619");
			//Ambiente dev Daniel
			//c = DriverManager.getConnection("jdbc:mysql://localhost/projeto_carnaval","root","root");
			//Ambiente dev Elom
			//c = DriverManager.getConnection("jdbc:mysql://localhost/projeto_carnaval","root","root");
			//Ambiente Produção
			//c = DriverManager.getConnection("jdbc:mysql://localhost/projeto_carnaval","root","root");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
