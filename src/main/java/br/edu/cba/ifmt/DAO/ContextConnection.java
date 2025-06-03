package br.edu.cba.ifmt.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContextConnection {
	private String jdbcURL = "jdbc:postgresql://localhost:5432/servlet-jsp-crud";
	private String jdbcUsername = "servlet-jsp-crud";
	private String jdbcPassword = "servlet-jsp-crud";

	public ContextConnection() {}

	protected Connection connection() throws Exception {
		Connection connection = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        System.out.println("Conexão estabelecida com sucesso");
	    } catch (SQLException e) {
	        System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        System.err.println("Driver JDBC não encontrado: " + e.getMessage());
	        e.printStackTrace();
	    }
		return connection;
	}
}