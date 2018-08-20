package com.timebank.screens;

import java.util.Scanner;

import com.timebank.daos.AdminDao;
import com.timebank.daos.UserDao;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AdminDao ad = AdminDao.currentAdminDao;
	
	{
		System.out.println("Welcome to the Time Bank, where you can see the results of \n your reaping pile up.");
		System.out.println();
	}
	
	public Screen start() {
		System.out.println("If you already have an  account, Enter the number '1' ");
		System.out.println("To Create an account, Enter '2'");
		System.out.println("To login as admin, Enter '3'");
		String choice = scan.nextLine();
		String username;
		String password;
		switch (choice) {
		case "1":
			System.out.println("Please enter your username: ");
			username = scan.nextLine();
			System.out.println("And your password? ");
			password = scan.nextLine();
			current.user = ud.findByUserNameAndPassword(username, password);
			if( (current.user != null) ) {
				return new UserScreen();
			}
			System.out.println("We cannot find the that account at this time.");
			break;
		case "2":
			return new SignUpScreen();

		case "3":
			System.out.println("Please enter your username: ");
			username = scan.nextLine();
			System.out.println("And your password? ");
			password = scan.nextLine();
			current.admin = ad.findByUserNameAndPassword(username, password);
			if( (current.admin != null) ) {
				return new AdminScreen();
			}
			break;
		default:
			System.out.println("That's not a valid option, please try again.");
			choice = scan.nextLine();
			break;
		}
		return this;
	}
}
