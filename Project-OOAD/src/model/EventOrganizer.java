package model;

import util.Connect;

public class EventOrganizer extends User {
	private static Connect connect = Connect.getInstance();

	public EventOrganizer(String id, String email, String username, String password, String role) {
		super(id, email, username, password, role);
	}

	public static void createEvent(String name, String date, String location, String description, String organizerId) {

	}

	public static void checkCreateEventInput(String name, String date, String location, String description) {

	}

	public static void viewOrganizedEvents() {

	}

}
