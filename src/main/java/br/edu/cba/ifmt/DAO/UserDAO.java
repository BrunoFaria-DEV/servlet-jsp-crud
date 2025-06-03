package br.edu.cba.ifmt.DAO;

import java.sql.Connection;

public class UserDAO {
	private String jdbcURL = "jdbc:postgres://localhost:5432/servlet-jsp-crud";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "postgres";
	
	private static final String SELECT_BY_ID = "SELECT * FROM `servlet-jsp-crud` WHERE id = `?`";
	private static final String SELECT_ALL = "SELECT * FROM `servlet-jsp-crud`";
	private static final String INSERT = "INSERT INTO `servlet-jsp-crud` (`nome`, `email`, `CPF`) VALUES(?, ?, ?, ?)";
	private static final String UPDATE = "SELECT * FROM `servlet-jsp-crud`";
	private static final String DELETE = "SELECT * FROM `servlet-jsp-crud`";
	
	public UserDAO() {}
	
	protected Connection connection() {
		Class.class("org.postgresql.Driver");
		return new Connection();
	}
}
