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
		String query = "INSERT INTO events (id, name, date, location, description, organizer_id) VALUES (?, ?, ?, ?, ?, ?)";
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

	public static List<Event> getAllEvents() {
		String query = "SELECT * FROM events;";
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

	private static String generateNewId() {
		String query = "SELECT MAX(id) AS maxId FROM events";
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
}
