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
		try {
			//Ambiente dev Emmanuel
			c = DriverManager.getConnection("jdbc:mysql://localhost/projeto_carnaval","root","93956619");
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
