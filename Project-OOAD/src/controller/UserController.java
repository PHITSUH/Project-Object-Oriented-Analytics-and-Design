package controller;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Event;
import model.EventOrganizer;
import model.Invitation;
import model.User;
import model.Vendor;
import util.Result;
import view.LoginPage;
import view.RegisterPage;
import view.ViewEventPage;
import view.ViewInvitationPage;

public class UserController extends Controller {

	public static ArrayList<User> userList = new ArrayList<>();

	public static void viewLoginPage() {
		navigate(new LoginPage());
	}

	public static void viewRegisterPage() {
		navigate(new RegisterPage());
	}

	public static void logout() {
		User.logout();
		navigate(new LoginPage());
	}

	public static Result<User, String> login(String email, String password) {
		// show the correct page for each role
		Result<User, String> user = User.login(email, password);

		if (user.isErr()) {
			return user;
		}
		User u = user.getValue();
		if (u instanceof Admin) {
//			navigate(new AdminPage());
		} else if (u instanceof Vendor) {
			navigate(new ViewInvitationPage(), Invitation.getInvitationsByEmail(email));
		} else if (u instanceof EventOrganizer) {
			navigate(new ViewEventPage(), Event.getEventByOrganizerId());
		} else {
			navigate(new ViewInvitationPage(), Invitation.getInvitationsByEmail(email));
		}

		return user;
	}

	public static void viewInvitation() {
		List<Invitation> invitationList = new ArrayList<>();
		invitationList = Invitation.getInvitationsByEmail(User.getCurrentUser().getEmail());

		navigate(new ViewInvitationPage(), invitationList);
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
		if (validation.isErr()) {
			return validation;
		}
		// if validation succeeds insert the user to the database
		User.register(email, name, password, role);
		// redirect to login page
		viewLoginPage();
		return Result.ok(null);
	}

}
