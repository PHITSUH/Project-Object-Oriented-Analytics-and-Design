package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Invitation extends Model {

	private String invitationId;

	private String eventId;
	private String userId;
	private String invitationStatus;
	private String invitationRole;

	public String getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(String invitationStatus) {
		this.invitationStatus = invitationStatus;
	}

	public String getInvitationRole() {
		return invitationRole;
	}

	public void setInvitationRole(String invitationRole) {
		this.invitationRole = invitationRole;
	}

	public Invitation(String invitationId, String eventId, String userId, String invitationStatus,
			String invitationRole) {
		super();
		this.invitationId = invitationId;
		this.eventId = eventId;
		this.userId = userId;
		this.invitationStatus = invitationStatus;
		this.invitationRole = invitationRole;
	}

	public static void sendInvitation(Event event, User user) {
		String query = "INSERT INTO invitation (invitationId, eventId, userId, invitationStatus, invitationRole) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.addQuery(query);
		String newId = generateNewId();

		try {
			ps.setString(1, newId);
			ps.setString(2, event.getId());
			ps.setString(3, user.getId());
			ps.setString(4, "Waiting to be accepted");
			ps.setString(5, user.getRole());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String acceptInvitation(String invitationId) {
		String query = "UPDATE invitation SET invitationStatus = ? WHERE invitationId = ?";
		PreparedStatement ps = connect.addQuery(query);

		try {
			ps.setString(1, "Accepted");
			ps.setString(2, invitationId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return "Invitation Accepted";
	}

	public static Boolean isInvited(String eventId, String userId) {
		String query = "SELECT InvitationId FROM Invitation WHERE UserId LIKE ? AND EventId LIKE ?";
		PreparedStatement ps = connect.addQuery(query);
		String check = "";
		try {
			ps.setString(1, userId);
			ps.setString(2, eventId);
			connect.rs = ps.executeQuery();
			while (connect.rs.next()) {
				check = connect.rs.getString("InvitationId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check.isEmpty() ? false : true;

	}

	public static List<Invitation> getInvitationsByEmail(String email) {
		String query = "SELECT * FROM invitation i JOIN users u ON i.userId = u.userId WHERE userEmail = ?";
		PreparedStatement ps = connect.addQuery(query);
		ArrayList<Invitation> resArr = new ArrayList<>();

		try {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String invitationId = rs.getString("id");
				String eventId = rs.getString("eventId");
				String userId = rs.getString("userId");
				String invitationStatus = rs.getString("invitationStatus");
				String invitationRole = rs.getString("invitationRole");

				Invitation inv = new Invitation(invitationId, eventId, userId, invitationStatus, invitationRole);

				resArr.add(inv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return resArr;
	}

	private static String generateNewId() {
		String query = "SELECT MAX(invitationId) AS maxId FROM Invitation";
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
