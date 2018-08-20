package com.timebank.daos;

import com.timebank.beans.User;

public interface UserDao {
	public static final UserDao currentUserDao = new  UserDaoJbdc();
	
	void createUser(User u);
	User findByUserNameAndPassword(String username, String password);
	void updateUser(User u);
	void deleteUser(User u);
}
