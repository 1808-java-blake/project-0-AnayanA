package com.timebank.launcher;

import com.timebank.screens.LoginScreen;
import com.timebank.screens.Screen;

public class Launcher {
		
	public static void main(String[] args) {
		Screen s = new LoginScreen();
		while(true) {
			s = s.start();
		}
	}
}
