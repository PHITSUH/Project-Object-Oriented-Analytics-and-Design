package controller;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;
import view.AdminPage;
import view.EventOrganizerPage;
import view.GuestPage;
import view.VendorPage;

public class UserController {

	public static ArrayList<User> userList = new ArrayList<>();

	public static void loginByRole(String email, Scene scene) {
		userList = User.getUser();
		User user = null;
		for (User u : userList) {
			if (u.getEmail().equals(email)) {
				user = u;
				break;
			}
		}
		if (user.getRole().equals("Event Organizer")) {
			new EventOrganizerPage(scene, user.getId());
		} else if (user.getRole().equals("Admin")) {
			new AdminPage(scene, user.getId());
		} else if (user.getRole().equals("Guest")) {
			new GuestPage(scene, user.getId());
		} else {
			new VendorPage(scene, user.getId());
		}

	}

	public static User getUserById(int id) {
		userList = User.getUser();
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId() == id) {
				return userList.get(i);
			}
		}
		return null;

	}

	public static Alert validateLogin(String email, String password) {
		Alert alert = new Alert(AlertType.ERROR);
		userList = User.getUser();
		if (email == null || email.isEmpty()) {
			alert.setContentText("Email must be filled");
			return alert;
		}
		if (password == null || password.isEmpty()) {
			alert.setContentText("Password must be filled");
			return alert;
		}
		int i = 0;
		User user = null;
		for (User u : userList) {
			if (u.getEmail().equals(email)) {
				i++;
				if (!u.getPassword().equals(password)) {
					alert.setContentText("Password is wrong");
					return alert;
				} else {
					user = u;
					break;
				}
			}
		}
		if (i == 0) {
			alert.setContentText("Email is wrong");
			return alert;
		}
		alert.setAlertType(AlertType.CONFIRMATION);
		alert.setContentText("Welcome, " + user.getUsername());
		return alert;
	}

	public static Alert validateRegister(String email, String username, String password, String role) {
		userList = User.getUser();
		Alert alert = new Alert(AlertType.ERROR);
		if (email == null || email.isEmpty()) {
			alert.setContentText("Email must be filled");
			return alert;
		}

		if (username == null || username.isEmpty()) {
			alert.setContentText("Username must be filled");
			return alert;
		}

		if (password == null || password.isEmpty()) {
			alert.setContentText("Password must be filled");
			return alert;
		}

		if (role == null || role.isEmpty()) {
			alert.setContentText("Must choose a role");
			return alert;
		}

		for (User u : userList) {
			if (u.getUsername() == username) {
				alert.setContentText("Username must be unique");
				return alert;
			}
			if (u.getEmail() == email) {
				alert.setContentText("Email must be unique");
				return alert;
			}
		}

		User.addUser(email, username, password, role);

		if (password.length() < 5) {
			alert.setContentText("Password must at least be 5 letters long");
			return alert;
		}

		alert.setAlertType(AlertType.CONFIRMATION);
		alert.setContentText("Account has been successfully created!");

		return alert;
	}

}
