package com.timebank.beans;

import java.io.Serializable;

public class Admin extends BasicInfo implements Serializable{
	private final boolean isAdmin = true;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		// TODO Auto-generated constructor stub
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isAdmin ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (isAdmin != other.isAdmin)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin [isAdmin=" + isAdmin + "]";
	}

	
	
}
