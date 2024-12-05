package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Connect;

public class Event {
	private String id;
	private String name, date, location, description;
	private String organizerId;
	private static Connect connect = Connect.getInstance();

	private Event(String id, String name, String date, String location, String description, String organizerId) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.location = location;
		this.description = description;
		this.organizerId = organizerId;
	}

	public

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

	private static String generateNewId() {
		String query = "SELECT MAX(id) AS maxId FROM events";
		PreparedStatement ps = null;
		int maxId = 0;

		try {
			ps = connect.addQuery(query);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve max ID and increment it
				String maxIdStr = rs.getString("maxId");
				if (maxIdStr != null) {
					maxId = Integer.parseInt(maxIdStr);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Increment and format the new ID
		maxId += 1;
		return String.format("%05d", maxId);
	}
}
