package dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO {
	protected Connection getConnection(){
		return Conexao.getConexao();
	}
	protected void closeConnection(Connection c){
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
