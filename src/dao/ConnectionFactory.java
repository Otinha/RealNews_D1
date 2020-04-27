package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("Não foi possível encontrar o driver de conexão com o banco de dados!");
		}	
	}
	
	public static Connection conectar() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/portal_realnews?serverTimezone=UTC", "root", "djow2001");
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
