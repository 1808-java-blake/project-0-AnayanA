package com.timebank.beans;

import java.io.Serializable;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User extends BasicInfo implements Serializable {
	
	private static final long serialVersionUID = -5061395406271069118L;
	
	private HashMap <String, Integer> accounts;
	private List <String> transactionHistory;
	private final boolean isAdmin = false;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		this.accounts = new HashMap<>();
		this.transactionHistory  = new ArrayList<>();
	}
	public void getAccounts() {
		this.accounts.entrySet().stream().forEach(item ->
				System.out.println(item.getValue() + ": \\$" + AccountMethods.repo.get(item.getKey()))
				);
	}
	public Integer getAccounts(String s) {
		return accounts.get(s);
	}
	public void setAccounts(String s, int i) {
		this.accounts.put(s, i);
	}
	public List<String> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<String> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((transactionHistory == null) ? 0 : transactionHistory.hashCode());
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
		User other = (User) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (transactionHistory == null) {
			if (other.transactionHistory != null)
				return false;
		} else if (!transactionHistory.equals(other.transactionHistory))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [firstName =" + getFirstName() + "lastName = "+ getLastName() + "accounts=" + accounts + ", transactionHistory=" + transactionHistory + ", isAdmin=" + isAdmin
				+ "]";
	}
	
	

	
	
}
