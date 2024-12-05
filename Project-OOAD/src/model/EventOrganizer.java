package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Connect;

public class EventOrganizer extends User {
	private static Connect connect = Connect.getInstance();

	public EventOrganizer(String id, String email, String username, String password, String role) {
		super(id, email, username, password, role);
	}

	public List<Event> viewOrganizedEvents() {
		String query = "SELECT * FROM event WHERE OrganizerId LIKE " + User.getCurrentUser().getId();
		connect.rs = connect.execQuery(query);
		ArrayList<Event> eventList = new ArrayList<>();
		try {
			while (connect.rs.next()) {
				String eventId = connect.rs.getString("EventId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
