package com.timebank.daos;

import com.timebank.beans.Admin;

public interface AdminDao {
	public static final AdminDao currentAdminDao = new  AdminDaoJbdc();
	
	void createAdmin(Admin u);
	Admin findByUserNameAndPassword(String username, String password);
	void updateAdmin(Admin u);
	void deleteAdmin(Admin u);
}
