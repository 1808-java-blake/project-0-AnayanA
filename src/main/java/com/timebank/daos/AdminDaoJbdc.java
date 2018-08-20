package com.timebank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.timebank.beans.Admin;
import com.timebank.util.ConnectionUtil;

public class AdminDaoJbdc implements AdminDao {
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void createAdmin(Admin a) {
		try (Connection con = cu.getConnection()){
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO admin_users (username,pass,firstname,lastname) VALUES (?,?,?,?)");
			ps.setString(1, a.getUsername());
			ps.setString(2, a.getPassword());;
			ps.setString(3, a.getFirstName());
			ps.setString(4, a.getLastName());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
			
		} catch (SQLException e) {
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new admin");
		}
		
	}

	@Override
	public Admin findByUserNameAndPassword(String username, String password) {
		try (Connection con = cu.getConnection()){
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM admin_users WHERE username=? and pass=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Admin a = new Admin();
				a.setFirstName(rs.getString("firstname"));
				a.setLastName(rs.getString("lastname"));
				a.setUsername(rs.getString("username"));
				a.setId(rs.getInt("admin_id"));
				return a;
			}else {
				log.warn("failed to find admin with provided credentials from the db");
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateAdmin(Admin a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAdmin(Admin a) {
		// TODO Auto-generated method stub

	}

}
