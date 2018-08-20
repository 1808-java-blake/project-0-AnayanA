package com.timebank.screens;

import java.util.List;
import java.util.Scanner;

import com.timebank.beans.AccountMethods;

public class AdminScreen implements Screen {
	private AccountMethods am = new AccountMethods();
	private Scanner scan = new Scanner(System.in);

	public Screen start() {
		System.out.println("Welcome to the admin menu. would you like to:");
		System.out.println("1. View an members accounts");
		System.out.println("2. View all Transaction history.");
		System.out.println("3. View a members Transaction history.");
		System.out.println("4. Logout.");
		String selection = scan.nextLine();
		String s;
		switch (selection) {
		case "1":
			System.out.println("Enter the Id of the user:"); 
			s = scan.nextLine();
			am.viewAccount(Integer.valueOf(s));
			am.promptEnterKey();
			break;
		case "2":
			List<String> allHis = am.viewAllTransHis();
			allHis.stream().forEach((each) -> {
				System.out.println(each);
			});
			am.promptEnterKey();
			break;
		case "3":
			System.out.println("Enter the Id of the user:");
			s = scan.nextLine();
			am.viewUserTransHis(Integer.valueOf(s));
			am.promptEnterKey();
			break;
		case "4":
			return new LoginScreen();

		default:
			break;
		}
		return this;
	}
}
