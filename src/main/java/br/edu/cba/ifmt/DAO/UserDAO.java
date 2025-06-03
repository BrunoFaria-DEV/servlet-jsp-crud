package br.edu.cba.ifmt.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.cba.ifmt.Model.User;

public class UserDAO {
	private ContextConnection _contextConnection;
	
	private static final String SELECT_ALL = "SELECT * FROM \"usuarios\"";
	private static final String SELECT_BY_ID = "SELECT * FROM \"usuarios\" WHERE \"id\" = ?";
	private static final String INSERT = "INSERT INTO \"usuarios\" (\"nome\", \"email\", \"CPF\", \"municipio_id\") VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE \"usuarios\" SET \"nome\" = ?, \"email\" = ?, \"CPF\" = ?, \"municipio_id\" = ? WHERE \"id\" = ?";
	private static final String DELETE = "DELETE FROM \"usuarios\" WHERE \"id\" = ?";
	
	public UserDAO() {
		_contextConnection = new ContextConnection();
	}
	
	public List<User> getAll() {
		List<User> users = new ArrayList<>();		

		try {
			PreparedStatement statement = _contextConnection.connection().prepareStatement(SELECT_ALL);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				User user = new User();
				user.setNome(result.getString("nome"));
				user.setEmail(result.getString("email"));
				user.setCPF(result.getString("CPF"));
				user.setMunicipio_id(result.getInt("municipio_id"));
				users.add(user);
			}
			statement.close();
			result.close();
			_contextConnection.connection().close();
		} catch (Exception e) {
            System.err.println("Erro em UserDAO.getAll(): " + e.getMessage());
			e.printStackTrace();
		}
		return users;
	}
	
	public User getById(int id) {
		User user = new User();		

		try {
			PreparedStatement statement = _contextConnection.connection().prepareStatement(SELECT_BY_ID);
			ResultSet result = statement.executeQuery();
			
			user.setNome(result.getString("nome"));
			user.setEmail(result.getString("email"));
			user.setCPF(result.getString("CPF"));
			user.setMunicipio_id(result.getInt("municipio_id"));

			statement.close();
			result.close();
			_contextConnection.connection().close();
		} catch (Exception e) {
            System.err.println("Erro em UserDAO.getAll(): " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean add(User user) {
		boolean operation = false;
		
		try {
			PreparedStatement statement = _contextConnection.connection().prepareStatement(INSERT);
			statement.setString(1, user.getNome());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCPF());
			statement.setInt(4, user.getMunicipio_id());
			
			operation = statement.executeUpdate() > 0;
			
			statement.close();
			_contextConnection.connection().close(); 
		} catch (Exception e) {
            System.err.println("Erro em UserDAO.getAll(): " + e.getMessage());
			e.printStackTrace();
		}
		return operation;
	}
	
	public boolean update(int id, User user) {
		boolean operation = false;
		
		try {
			User registeredUser = getById(id);
			if (registeredUser == null) {
				return false;
			}
			
			PreparedStatement statement = _contextConnection.connection().prepareStatement(UPDATE);
			statement.setString(1, user.getNome());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCPF());
			statement.setInt(4, user.getMunicipio_id());
			statement.setInt(5, id);
			
			operation = statement.executeUpdate() > 0;
			
			statement.close();
			_contextConnection.connection().close(); 
		} catch (Exception e) {
            System.err.println("Erro em UserDAO.getAll(): " + e.getMessage());
			e.printStackTrace();
		}
		return operation;
	}
	
	public boolean delete(int id) {
		boolean operation = false;
		
		try {
			User registeredUser = getById(id);
			if (registeredUser == null) {
				return false;
			}
			
			PreparedStatement statement = _contextConnection.connection().prepareStatement(DELETE);
			statement.setInt(1, id);
			
			operation = statement.executeUpdate() > 0;
			
			statement.close();
			_contextConnection.connection().close(); 
		} catch (Exception e) {
            System.err.println("Erro em UserDAO.getAll(): " + e.getMessage());
			e.printStackTrace();
		}
		return operation;
	}
}
