package br.edu.cba.ifmt.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.cba.ifmt.Model.City;
import br.edu.cba.ifmt.Model.User;

public class UserDAO {
	private ContextConnection _contextConnection;
	
	private static final String SELECT_ALL = 	"SELECT u.*, m.\"id\" AS \"m_id\", m.\"nome\" AS \"m_nome\" " +
											    "FROM \"usuarios\" u " +
											    "JOIN \"municipios\" m ON u.\"municipio_id\" = m.\"id\"";
	private static final String SELECT_BY_ID = 	"SELECT u.*, m.\"id\" AS \"m_id\", m.\"nome\" AS \"m_nome\"  " +
											    "FROM \"usuarios\" u " +
											    "JOIN \"municipios\" m ON u.\"municipio_id\" = m.\"id\" " +
											    "WHERE u.\"id\" = ?";
	private static final String INSERT = 		"INSERT INTO \"usuarios\" (\"nome\", \"email\", \"CPF\", \"municipio_id\") VALUES (?, ?, ?, ?)";
	private static final String UPDATE = 		"UPDATE \"usuarios\" SET \"nome\" = ?, \"email\" = ?, \"CPF\" = ?, \"municipio_id\" = ? WHERE \"id\" = ?";
	private static final String DELETE = 		"DELETE FROM \"usuarios\" WHERE \"id\" = ?";
	
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
				user.setId(result.getInt("id"));
				user.setNome(result.getString("nome"));
				user.setEmail(result.getString("email"));
				user.setCPF(result.getString("CPF"));
				user.setCity(new City(result.getInt("m_id"), result.getString("m_nome")));
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
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) { 
				user.setId(result.getInt("id"));
				user.setNome(result.getString("nome"));
				user.setEmail(result.getString("email"));
				user.setCPF(result.getString("CPF"));
				user.setCity(new City(result.getInt("m_id"), result.getString("m_nome")));
			} else {
				user = null; 
			}

			statement.close();
			result.close();
			_contextConnection.connection().close();
		} catch (Exception e) {
            System.err.println("Erro em UserDAO.getById(): " + e.getMessage());
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
			statement.setInt(4, user.getCity().getId());
			
			operation = statement.executeUpdate() > 0;
			
			statement.close();
			_contextConnection.connection().close(); 
		} catch (Exception e) {
            System.err.println("Erro em UserDAO.add(): " + e.getMessage());
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
			statement.setInt(4, user.getCity().getId());
			statement.setInt(5, id);
			
			operation = statement.executeUpdate() > 0;
			
			statement.close();
			_contextConnection.connection().close(); 
		} catch (Exception e) {
            System.err.println("Erro em UserDAO.update(): " + e.getMessage());
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
            System.err.println("Erro em UserDAO.delete(): " + e.getMessage());
			e.printStackTrace();
		}
		return operation;
	}
}
