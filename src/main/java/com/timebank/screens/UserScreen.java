package com.timebank.screens;

import java.util.Scanner;

import com.timebank.beans.AccountMethods;

public class UserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountMethods am = new AccountMethods();

	public Screen start() {
		System.out.println("Welcome to your menu. would you like to:");
		System.out.println("1. View accounts");
		System.out.println("2. Deposit into an account");
		System.out.println("3. Withdraw from an account");
		System.out.println("4. Send money to another account");
		System.out.println("5. View Transaction history");
		System.out.println("6. Create a new account");
		System.out.println("7. Logout.");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			current.user.getAccounts();		
			break;
		case "2":
			System.out.println("Which account are you depositing to? Type the name please.");
			String acc = scan.nextLine();
			System.out.println("How much would you like to deposit?");
			Integer i = scan.nextInt();
			
			am.deposit(current.user, acc, i);
			break;
		case "3":

			break;
		case "4":

			break;
		case "5":

			break;
		case "6":

			break;
		case "7":

			break;

		default:
			break;
		}
		return this;
	}
}
