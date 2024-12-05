package controller;

import view.CreateEventPage;
import view.ViewEventPage;

public class EventOrganizerController extends Controller {
	public static void viewOrganizedEvents(String organizerId) {
		// fetch shit here
	}

	public static void viewCreateEventPage() {
		navigate(new CreateEventPage());
	}

	public static void viewViewEventPage() {
		navigate(new ViewEventPage());
	}
}
