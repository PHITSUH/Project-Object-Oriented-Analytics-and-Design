package model;

public class EventOrganizer extends User {

	public EventOrganizer(String id, String email, String username, String password, String role) {
		super(id, email, username, password, role);
	}

	public static void createEvent(String name, String date, String location, String description, String organizerId) {

	}

	public static void viewOrganizedEvent(String userId) {

	}

	public static void viewOrganizedEventDetails(String eventId) {

	}

	public static void getGuestsByTransactionId(String eventId) {
		Guest.getGuestsByTransactionID(eventId);
	}

	public static void getVendorsByTransactionId(String eventId) {
		Vendor.getVendorsByTransactionID(eventId);
	}

	public static void checkCreateEventInput(String name, String date, String location, String description) {

	}

	public static void checkAddVendorInput(String vendorId) {

	}

	public static void checkAddGuestInput(String vendorId) {

	}

	public static void viewOrganizedEvents() {

	}

	public static void GetVendors() {
		Vendor.getAllVendors();
	}

	public static void getGuests() {
		Guest.getAllGuests();
	}

}
