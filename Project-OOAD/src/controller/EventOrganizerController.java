package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Guest;
import model.User;
import model.Vendor;
import util.Result;
import view.CreateEventPage;
import view.EventDetailsPage;
import view.EventDetailsPage.Props;
import view.ViewEventPage;

public class EventOrganizerController extends Controller {

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

	public static Result<Void, String> checkCreateEventInput(String name, LocalDate date, String location,
			String description) {
		if (name == null || name.isEmpty())
			return Result.err("Name is Empty");

		if (date == null)
			return Result.err("Date is Empty");

		if (date.isBefore(LocalDate.now()))
			return Result.err("Date must be in the future");

		if (location == null || location.isEmpty())
			return Result.err("Location is Empty");

		if (location.length() < 5)
			return Result.err("Location must be at least 5 characters long");

		if (description == null || description.isEmpty())
			return Result.err("Description is Empty");

		if (description.length() > 200)
			return Result.err("Description must be less than 200 characters long");

		return Result.ok(null);
	}

	public static Result<Void, String> createEvent(String name, LocalDate date, String location, String description,
			String organizerId) {
		Result<Void, String> check = checkCreateEventInput(name, date, location, description);
		if (check.isErr())
			return check;
		Event.createEvent(name, date.toString(), location, description, organizerId);
		viewOrganizedEvents();
		return Result.ok(null);

	}

	public static void viewOrganizedEvents() {
		// fetch shit here
		List<Event> eventList = Event.getEventByOrganizerId();
		navigate(new ViewEventPage(), eventList);
	}

	public static void viewCreateEventPage() {
		navigate(new CreateEventPage());
	}
}
