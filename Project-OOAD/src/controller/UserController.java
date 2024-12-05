package controller;

import java.util.ArrayList;

import model.User;
import util.Result;

public class UserController {

	public static ArrayList<User> userList = new ArrayList<>();

	public static Result<User, String> login(String email, String password) {
		return User.login(email, password);
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
		if (role == null || role.isEmpty()) {
			return Result.err("You must choose a role!");
		}

		Result<Void, String> validation = checkRegisterInput(email, name, password);
		if (!validation.isOk()) {
			return validation;
		}
		// if validation succeeds insert the user to the database
		User.register(email, name, password, role);
		return Result.ok(null);
	}

}
