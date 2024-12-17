package model;

import java.util.List;

public class Admin extends User {

	public Admin(String id, String email, String name, String password, String role) {
		super(id, email, name, password, role);
	}

	public void viewAllEvents() {

	}

	public static List<User> getAllUsers() {
		return User.getAllUsers();
	}

	public static List<Event> getAllEvents() {
		return Event.getAllEvents();
	}

}
