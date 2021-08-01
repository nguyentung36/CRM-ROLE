package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import crmapp.model.Project;
import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.RoleCreateDto;
import cybersoft.java12.crmapp.model.Role;

public class RoleDao {

	public List<Role> findAll() throws SQLException {
		List<Role> roles = new LinkedList<>();

		try {
			Connection connection = MySqlConnection.getConnection();	
			String query = "SELECT * FROM role";
			
			PreparedStatement statement = connection.prepareStatement(query); 
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Role role = new Role();
				
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				
				roles.add(role);
			}
			connection.close();
			
		} catch (SQLException e) {
		    System.out.println("Unable to connect to database. ");
			e.printStackTrace();
		} 
		return roles;
	}

	public void deleteRoleById(int id) throws SQLException{
		String query = "DELETE FROM role WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
		    System.out.println("Unable to connect to database. ");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}

	public void add(RoleCreateDto dto) throws SQLException {
		String query = "INSERT INTO role(name, description)"
				+ "VALUES(?,?)";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getDescription());
			
			statement.executeUpdate();
		
		} catch (SQLException e) {
		    System.out.println("Unable to connect to database. ");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void update(RoleCreateDto roleUpdate, int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "UPDATE role SET name = ?, description = ? ";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, roleUpdate.getName());
			statement.setString(2, roleUpdate.getDescription());
			
			
			statement.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}

	public Role findRoleById(int id) {
           Role role = null;
		
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "SELECT id, name, description FROM role WHERE id = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				role = new Role();
				role.setId(result.getInt("id"));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection to database has been disconnected!");
		}
		return role;
	}

	

	

}
