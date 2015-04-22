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
/*		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/aulapos","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return c;
	}

}
