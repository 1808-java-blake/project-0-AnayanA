package com.timebank.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.timebank.beans.User;

public class UserSerializer implements UserDao{
	public static final UserSerializer us = new UserSerializer();
	private Logger log = Logger.getRootLogger();

	public UserSerializer() {
		super();
	}

	@Override
	public void createUser(User u) {
		if( u == null) {
			return;
		}
		File f = new File(("src/main/resources/users/") + u.getUsername() + ".txt");
		if (f.exists()) {
			return;
		}try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/users/" + u.getUsername() + ".txt"))) {

			oos.writeObject(u);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public User findByUserNameAndPassword(String username, String password) {
		// verify that what was passed in is not null
				log.trace(password);
				if (username == null || password == null) {
					return null;
				}
				try (ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("src/main/resources/users/" + username + ".txt"))) {

					User u = (User) ois.readObject(); // retrieve the user if it can
					log.trace(u);
					if (password.equals(u.getPassword())) {
						return u;
					} else {
						return null;
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
		return null;
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}
}

