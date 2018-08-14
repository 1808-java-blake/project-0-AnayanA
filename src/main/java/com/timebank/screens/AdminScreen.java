package com.timebank.screens;

import java.util.Scanner;

public class AdminScreen implements Screen {

	private Scanner scan = new Scanner(System.in);

	public Screen start() {
		System.out.println("Welcome to the admin menu. would you like to:");
		System.out.println("1. View an members account");
		System.out.println("2. View all Transaction history.");
		System.out.println("3. View members Transaction history.");
		System.out.println("7. Logout.");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			
			break;
		case "2":

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
