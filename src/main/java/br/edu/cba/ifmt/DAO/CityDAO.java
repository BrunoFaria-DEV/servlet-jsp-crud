package br.edu.cba.ifmt.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.cba.ifmt.Model.City;

public class CityDAO {	
	private ContextConnection _contextConnection;
	
	private static final String SELECT_ALL = "SELECT * FROM \"municipios\"";
	private static final String SELECT_BY_ID = "SELECT * FROM \"municipios\" WHERE \"id\" = ?";
	
	public CityDAO() {
		_contextConnection = new ContextConnection();
	}
	
	public List<City> getAll() {
		List<City> cities = new ArrayList<>();		

		try {
			PreparedStatement statement = _contextConnection.connection().prepareStatement(SELECT_ALL);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				City city = new City();
				city.setId(result.getInt("id"));
				city.setNome(result.getString("nome"));
				cities.add(city);
			}
			
			statement.close();
			result.close();
			_contextConnection.connection().close();
		} catch (Exception e) {
            System.err.println("Erro em CityDAO.getAll(): " + e.getMessage());
			e.printStackTrace();
		}
		return cities;
	}
	
	public City getById(int id) {
		City city = new City();		

		try {
			PreparedStatement statement = _contextConnection.connection().prepareStatement(SELECT_BY_ID);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {			
				city.setId(result.getInt("id"));
				city.setNome(result.getString("nome"));
			} else {
				city = null; 
			}

			statement.close();
			result.close();
			_contextConnection.connection().close();
		} catch (Exception e) {
            System.err.println("Erro em CityDAO.getById(): " + e.getMessage());
			e.printStackTrace();
		}
		return city;
	}
}
