package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.crmapp.dao.RoleDao;
import cybersoft.java12.crmapp.dto.RoleCreateDto;
import cybersoft.java12.crmapp.model.Role;

public class RoleService {
	private RoleDao dao;
	
	public  RoleService() {
		dao = new RoleDao();
	}

	public List<Role> findAll() {
		List<Role> roles = null;
		try {
			return dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return roles;
	}

	public void deleteRoleById(int id) {
		try {
			dao.deleteRoleById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void add(RoleCreateDto dto) {
		try {
			dao.add(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void update(RoleCreateDto roleUpdate, int id) {
		try {
			dao.update(roleUpdate, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Role findRoleById(int id) {
		return dao.findRoleById(id);
	}

	

}
