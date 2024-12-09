package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Event extends Model {
	private String id;
	private String name, date, location, description;
	private String organizerId;

	public Event(String id, String name, String date, String location, String description, String organizerId) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.location = location;
		this.description = description;
		this.organizerId = organizerId;
	}

	public static Event createEvent(String name, String date, String location, String description, String organizerId) {
		String query = "INSERT INTO event (EventId, EventName, EventDate, EventLocation, EventDescription, OrganizerId) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.addQuery(query);
		String newId = generateNewId();

		try {
			ps.setString(1, newId);
			ps.setString(2, name);
			ps.setString(3, date);
			ps.setString(4, location);
			ps.setString(5, description);
			ps.setString(6, organizerId);
			ps.executeUpdate();

			return new Event(newId, name, date, location, description, organizerId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Event> getEventByOrganizerId() {
		String query = "SELECT * FROM event WHERE OrganizerId LIKE ?";
		PreparedStatement ps = connect.addQuery(query);
		try {
			ps.setString(1, User.getCurrentUser().getId());
			connect.rs = ps.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Event> eventList = new ArrayList<>();

		try {
			while (connect.rs.next()) {

				String eventId = connect.rs.getString("EventId");
				String eventName = connect.rs.getString("EventName");
				String eventDate = connect.rs.getString("EventDate");
				String eventLocation = connect.rs.getString("EventLocation");
				String eventDescription = connect.rs.getString("EventDescription");
				String organizerId = connect.rs.getString("OrganizerId");
				eventList.add(new Event(eventId, eventName, eventDate, eventLocation, eventDescription, organizerId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventList;
	}

	public static List<Event> getAllEvents() {
		String query = "SELECT * FROM event;";
		PreparedStatement ps = null;
		List<Event> events = new ArrayList<>();

		try {
			ps = connect.addQuery(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String id = rs.getString("EventId");
				String name = rs.getString("EventName");
				String date = rs.getString("EventDate");
				String location = rs.getString("EventLocation");
				String description = rs.getString("EventDescription");
				String organizerId = rs.getString("OrganizerId");

				events.add(new Event(id, name, date, location, description, organizerId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}

	public static Event getEventById(String eventId) {
		String query = "SELECT * FROM Event WHERE EventId LIKE '" + eventId + "'";
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				String name = connect.rs.getString("EventName");
				String date = connect.rs.getString("EventDate");
				String loc = connect.rs.getString("EventLocation");
				String desc = connect.rs.getString("EventDescription");
				String organizerId = connect.rs.getString("OrganizerId");
				return new Event(eventId, name, date, loc, desc, organizerId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String generateNewId() {
		String query = "SELECT MAX(EventId) AS maxId FROM event";
		PreparedStatement ps = null;
		int maxId = 0;

		// take the biggest ID
		try {
			ps = connect.addQuery(query);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String maxIdStr = rs.getString("maxId");
				if (maxIdStr != null) {
					maxId = Integer.parseInt(maxIdStr);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// increment and format the new ID
		maxId += 1;
		return String.format("%05d", maxId);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(String organizerId) {
		this.organizerId = organizerId;
	}

}
