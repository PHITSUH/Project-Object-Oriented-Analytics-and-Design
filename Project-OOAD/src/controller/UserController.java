package controller;

import java.util.ArrayList;

import javafx.scene.Scene;
import model.User;
import util.Result;
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

	public static Result<Void, String> validateLogin(String email, String password) {

	}

	private static Result<Void, String> checkRegisterInput(String email, String name, String password) {
		// isBlank lebih bagus daripada isEmpty
		// karena isBlank juga ngecek apakah inputnya hanya spasi doang
		if (email == null || email.isBlank()) {
			return Result.err("Email must be filled");
		}

		if (name == null || name.isBlank()) {
			return Result.err("Username must be filled");
		}

		if (password == null || password.isBlank()) {
			return Result.err("Password must be filled");
		}

		if (password.length() < 5) {
			return Result.err("Password must at least be 5 letters long");
		}
		return Result.ok(null);
	}

	public static Result<Void, String> register(String email, String name, String password, String role) {
		User userByEmail = User.getUserByEmail(email).orElse(null);
		User userByName = User.getUserByName(name).orElse(null);

		if (userByEmail != null) {
			return Result.err("Email must be unique");
		}
		if (userByName != null) {
			return Result.err("Name must be unique");
		}

		Result<Void, String> validation = checkRegisterInput(email, name, password);

		if (!validation.isOk()) {
			return validation;
		}

		User.register(email, name, password, role);
		return Result.ok(null);
	}

}
