package com.timebank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.timebank.beans.User;
import com.timebank.util.ConnectionUtil;

/* CREATE TABLE stan_user (	user_id SERIAL PRIMARY KEY,	username VARCHAR(10) NOT NULL UNIQUE,
	pass VARCHAR(16) NOT NULL,	firstname VARCHAR(15),	lastname VARCHAR (20) );*/

//CREATE TABLE accounts ( user_id SERIAL FOREIGN KEY NOT NULL, acc_num UNIQUE NOT NULL, balance NOT NULL);

public class UserDaoJbdc implements UserDao{
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
	public void createUser(User u) {
		try (Connection con = cu.getConnection()){
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO stan_user (username,pass,firstname,lastname) VALUES (?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());;
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
			
		} catch (SQLException e) {
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}
		
	}

	@Override
	public User findByUserNameAndPassword(String username, String password) {
		try (Connection con = cu.getConnection()){
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM stan_user WHERE username=? and pass=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setId(rs.getInt("user_id"));
				return u;
			}else {
				log.warn("failed to find user with provided credentials from the db");
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

}
