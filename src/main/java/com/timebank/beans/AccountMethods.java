package com.timebank.beans;

import java.util.HashMap;

import com.timebank.daos.UserSerializer;

public class AccountMethods {
	
	public static HashMap<Integer, Integer> repo = new HashMap<>();
	private UserSerializer us = new UserSerializer();
	
	public  void addAccount(User u, String string){
		Integer num = (int)(Math.random()* 999999) +1;
		while(repo.containsKey(num))
			num = (int)(Math.random()* 999999) +1;
		repo.put(num, 100);
		u.setAccounts(string, num);
		us.updateUser(u);
		return ;
	}
	
	public void deposit(User u, String s, int i) {
		Integer k = u.getAccounts(s);
		Integer v = repo.get(s);
		System.out.println("" + k + "" + v);
		repo.put(u.getAccounts(s), repo.get(s)+i);
		System.out.println("Amount deposited. The new balance is $" + repo.get(s));
		return;
	}
}
