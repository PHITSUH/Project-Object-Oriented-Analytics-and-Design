package model;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.UserController;
import util.Connect;

public class Event {
	private int eventId;
	private String eventName, eventDate, eventLocation, eventDescription;
	private ArrayList<User> attendees;
	private static Connect connect = Connect.getInstance();

	public Event(int eventId, String eventName, String eventDate, String eventLocation, String eventDescription,
			ArrayList<User> attendees) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		this.eventDescription = eventDescription;
		this.attendees = attendees;
	}

	public static ArrayList<User> getAttendees(int id) {
		ArrayList<User> attendeeList = new ArrayList<>();

		String query = "SELECT * FROM event_attendees WHERE eventId = " + id;
		connect.rs = connect.execQuery(query);
		attendeeList = new ArrayList<>();

		try {
			while (connect.rs.next()) {
				int userId = Integer.parseInt(connect.rs.getString("UserId"));

				attendeeList.add(UserController.getUserById(userId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attendeeList;
	}

	public static ArrayList<Event> getEventByUserId(int id) {
		ArrayList<Event> eventList = new ArrayList<>();

		String query = "SELECT * FROM events WHERE userId = " + id;
		connect.rs = connect.execQuery(query);
		System.out.println("test");
		eventList = new ArrayList<>();
		try {
			while (connect.rs.next()) {
				int eventId = Integer.parseInt(connect.rs.getString("EventId"));
				String eventName = connect.rs.getString("EventName");
				String eventDate = connect.rs.getString("Eventz`Date");
				String eventLocation = connect.rs.getString("EventLocation");
				String eventDescription = connect.rs.getString("EventDescription");
				ArrayList<User> attendeeList = getAttendees(id);
				eventList.add(new Event(eventId, eventName, eventDate, eventLocation, eventDescription, attendeeList));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return eventList;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public ArrayList<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<User> attendees) {
		this.attendees = attendees;
	}

}
