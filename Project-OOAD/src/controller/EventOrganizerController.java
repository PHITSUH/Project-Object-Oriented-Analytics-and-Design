package controller;

import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Guest;
import model.User;
import model.Vendor;
import view.CreateEventPage;
import view.EventDetailsPage;
import view.EventDetailsPage.Props;
import view.ViewEventPage;

public class EventOrganizerController extends Controller {

	public static void viewOrganizedEvents() {
		// fetch shit here
		navigate(new ViewEventPage());
	}

	public static void viewOrganizedEventDetails(String eventId) {
		List<User> participantList = new ArrayList<>();
		participantList.addAll(getGuestsByTransactionID(eventId));
		participantList.addAll(getVendorsByTransactionID(eventId));

		navigate(new EventDetailsPage(), new Props(Event.getEventById(eventId), participantList));
	}

	public static List<User> getGuestsByTransactionID(String eventId) {
		return Guest.getGuestsByTransactionID(eventId);
	}

	public static List<User> getVendorsByTransactionID(String eventId) {
		return Vendor.getVendorsByTransactionID(eventId);
	}

	public static void viewCreateEventPage() {
		navigate(new CreateEventPage());
	}
}
