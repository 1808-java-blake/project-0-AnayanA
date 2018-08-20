package com.timebank.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.timebank.beans.User;
import com.timebank.daos.UserDao;

public class SignUpScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private Logger log = Logger.getRootLogger();

	public Screen start() {
		
		User u = new User();
		System.out.println("Thank you for choosing to bank with us fellow reaper."
				+ "\n You won't regret leaving your hard-earned time with us.");
		System.out.println("But we would like some information from you before your precious time can get stored.");
		System.out.println("What's your first name? ");
		u.setFirstName(scan.nextLine());
		log.trace(u.getFirstName());
		
		System.out.println("Your last name? ");
		u.setLastName(scan.nextLine());
		log.trace(u.getLastName());

		
		System.out.println("Great, you're half way there. What's your desired username? ");
		u.setUsername(scan.nextLine());
		log.trace(u.getUsername());

		
		System.out.println("And finally your password: ");
		u.setPassword(scan.nextLine());
		log.trace(u.getPassword());

		ud.createUser(u);
		
		
		System.out.println("Congratz, you are now a member of Time Bank.\n");

		return new LoginScreen();
	}

}
