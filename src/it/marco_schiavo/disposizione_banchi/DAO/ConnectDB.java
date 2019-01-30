package it.marco_schiavo.disposizione_banchi.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/Alunno?user=root&password=LeonardoViola201";
	private static Connection conn;
	
	public static Connection getConnection() {
		
		try {
			if (conn==null || conn.isClosed()) {
				conn = DriverManager.getConnection(jdbcURL);
			}
		} catch (SQLException e) {
			System.err.println("Errore di connessione al DB");
			throw new RuntimeException();
			
		}
		return conn;
		
	}

}
