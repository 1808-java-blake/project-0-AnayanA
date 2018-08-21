package com.timebank.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import com.timebank.util.ConnectionUtil;

//Location for the Press enter to continue code I found
// https://stackoverflow.com/questions/26184409/java-console-prompt-for-enter-input-before-moving-on

public class AccountMethods {
	public static AccountMethods am = new AccountMethods();
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);

	public void addAccount(User u, String s) {
		Integer num = (int)(Math.random()* 999999) +1;
		try(Connection con = cu.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO account_hub (user_id, account_name, account_number) VALUES (?, ?, ?);\r\n" + 
					"INSERT INTO accounts (account_number, balance) VALUES (?, 0);\r\n" +
					"INSERT INTO trans_his (user_id, account_number, trans) VALUES (?, ?, ?);");
			ps.setInt(1, u.getId());
			ps.setString(2, s);
			ps.setInt(3, num);
			ps.setInt(4, num);
			ps.setInt(5, u.getId());
			ps.setInt(6, num);
			ps.setString(7, "Account Created");
			int accCreated = ps.executeUpdate();
			log.trace(accCreated + " account created");
			
		} catch (SQLException e) {
			e.printStackTrace();
			addAccount(u, s);
		}
		return;
	}

	public void deposit(User u, String s) {
		try (Connection con = cu.getConnection()){
			PreparedStatement ps = con.prepareStatement(
					"SELECT account_number FROM account_hub WHERE user_id = ? AND account_name = ?");		
			ps.setInt(1, u.getId());
			ps.setString(2, s);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int acc = rs.getInt("account_number");
				ps = con.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
				ps.setInt(1, acc);
				rs = ps.executeQuery();
				if(rs.next()) {
					System.out.println("How much have you reaped today? ");
					int inc = Integer.valueOf(scan.nextLine());
					ps = con.prepareStatement("UPDATE accounts SET balance = ? WHERE account_number = ?;\r\n" 
							+ "INSERT INTO trans_his (user_id, account_number, trans) VALUES (?, ?, ?);");
					ps.setInt(1, inc + rs.getInt("balance"));
					ps.setInt(2, acc);
					ps.setInt(3, u.getId());
					ps.setInt(4, acc);
					ps.setString(5, s+ ":" + inc + "t Saved");
					rs = ps.executeQuery();
				}
			}

		}catch (NumberFormatException e2) {
			System.out.println("You can't store words here, only souls :/");
			return;
		}catch (PSQLException e1){
			System.out.println("That's a good amount...");
			return;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
		System.out.println("Couldn't find that account, check your spelling.");
		return;	
	}

	public void withdraw(User u, String s) {
		try (Connection con = cu.getConnection()){
			PreparedStatement ps = con.prepareStatement(
					"SELECT account_number FROM account_hub WHERE user_id = ? AND account_name = ?");		
			ps.setInt(1, u.getId());
			ps.setString(2, s);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int acc = rs.getInt("account_number");
				ps = con.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
				ps.setInt(1, acc);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					System.out.println("How much are you using? ");
					int dec = Integer.valueOf(scan.nextLine());
					ps = con.prepareStatement("UPDATE accounts SET balance = ? WHERE account_number = ?;\r\n" 
							+ "INSERT INTO trans_his (user_id, account_number, trans) VALUES (?, ?, ?);");
					ps.setInt(1, rs.getInt("balance") - dec);
					ps.setInt(2, acc);
					ps.setInt(3, u.getId());
					ps.setInt(4, acc);
					ps.setString(5, s+ ": $" + dec + "t wasted");
					rs = ps.executeQuery();
				}
			}

		}catch (NumberFormatException e2) {
			System.out.println(":/, really? You should try the writers guild to withdraw letters.");
			return;
		}catch (PSQLException e1){
			System.out.println("Don't waste it.");
			return;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
		System.out.println("Couldn't find that account, check your spelling.");
		return;
	}
		

//	public void wireTransfer(User u) {
//		Integer v1 = repo.get(i);
//		Integer v2 = repo.get(k);
//
//		repo.put(i, v1 + j);
//		repo.put(k, v2 - j);
//		u.setTransactionHistory("$" + j + " has been removed from " + s + " and sent to account# :" + i);
//		System.out.println("$" + j + " has been removed from " + s + " and sent to account# :" + i);
//
//	}
//
//	public void wireTransfer(User u, String s, String t, int i) {
//		Integer k1 = u.getAccounts(s);
//		Integer k2 = u.getAccounts(t);
//		Integer v1 = repo.get(k1);
//		Integer v2 = repo.get(k2);
//
//		repo.put(k1, v1 - i);
//		repo.put(k2, v2 + i);
//
//		u.setTransactionHistory("$" + i + " has been removed from " + s + " and sent to " + t);
//		System.out.println("$" + i + " has been removed from " + s + " and sent to " + t + ". Your new balances are $"
//				+ repo.get(k1) + " and $" + repo.get(k2) + " respectively.");
//	}

	
	public List<String> viewUserTransHis(int userId) {
		try(Connection con = cu.getConnection()) {
			List<String> his = new ArrayList<>();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM trans_his WHERE user_id = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				his.add(rs.getString("trans"));
			}
			
			return his;
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<String> viewAllTransHis() {
		try(Connection con = cu.getConnection()) {
			List<String> allHis = new ArrayList<>();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM trans_his ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				allHis.add(rs.getString("trans"));
			}
			
			return allHis;
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public void promptEnterKey() {
		System.out.println("Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}

	public void viewAccount(int userId) {
		String s;
		try(Connection con = cu.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT account_name FROM account_hub WHERE user_id = ? ");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s = rs.getString("account_name");
				System.out.print(s + ", ");
			}
			
			System.out.println("\n Which account would you like to view?");
			s = scan.nextLine();
			ps = con.prepareStatement(
					"SELECT balance FROM accounts WHERE account_number IN \r\n" + 
					"(SELECT account_number FROM account_hub WHERE user_id = ? AND account_name = ?);");
			ps.setInt(1, userId);
			ps.setString(2, s);
			rs = ps.executeQuery();
			if(rs.next())
				System.out.println(s + ": " + rs.getInt("balance") + "t");
			
			return;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return;
	}
	
}
