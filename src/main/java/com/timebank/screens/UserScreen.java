package com.timebank.screens;

import java.util.List;
import java.util.Scanner;

import com.timebank.beans.AccountMethods;

public class UserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AccountMethods am = new AccountMethods();
	
	
	public Screen start() {
		System.out.println("Welcome to your menu "+ current.user.getFirstName() + 
				" " + current.user.getLastName()+ " would you like to:");
		System.out.println("1. View accounts");
		System.out.println("2. Deposit into an account");
		System.out.println("3. Withdraw from an account");
		System.out.println("4. Send money to another account");
		System.out.println("5. View recent transaction history");
		System.out.println("6. Create a new account");
		System.out.println("7. Logout.");
		String selection = scan.nextLine();
		String s;
		switch (selection) {
		case "1":
			am.viewAccount(current.user.getId());
			am.promptEnterKey();
			break;
		case "2":
			System.out.println("Which account are you depositing to? Type the name please.");
			s = scan.nextLine();
			am.deposit(current.user, s);
			am.promptEnterKey();
			break;
		case "3":
			System.out.println("Which account are you withdrawing from? Type the name please.");
			s = scan.nextLine();
			am.withdraw(current.user, s);
			am.promptEnterKey();
			break;
		case "4":
			System.out.println("System currently under maintainence.");
			//am.wireTransfer(current.user);
			am.promptEnterKey();
			break;
		case "5":
			List<String> his = am.viewUserTransHis(current.user.getId());
			his.stream().forEach((each) -> {
				System.out.println(each);
			});
			am.promptEnterKey();
			break;
		case "6":
			System.out.println("What would you like to call your account?");
			s = scan.nextLine();
			am.addAccount(current.user, s);
			am.promptEnterKey();
			break;
		case "7":
			return new LoginScreen();

		default:
			System.out.println("Invalid option, please try again.");
			break;
		}
		return this;
	}
}
