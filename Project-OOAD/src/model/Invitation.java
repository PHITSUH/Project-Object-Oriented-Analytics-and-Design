package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Model;

public class Invitation extends Model{

	private String invitationId;
	private String eventId;
	private String userId;
	private String invitationStatus;
	private String invitationRole;

	public Invitation(String invitationId, String eventId, String userId, String invitationStatus, String invitationRole) {
		super();
		this.invitationId = invitationId;
		this.eventId = eventId;
		this.userId = userId;
		this.invitationStatus = invitationStatus;
		this.invitationRole = invitationRole;
	}
	
	public void sendInvitation(String email) {
		invitationStatus = "Pending Acceptance";
	}
	
	public void acceptInvitation(String eventID) {
		invitationStatus = "Accepted";
	}
	
	public Invitation getInvitations(String email) {
		return this;
	}
	
	public static Invitation createInvitation(String invitationId, String eventId, String userId, String invitationRole) {
		String query = "INSERT INTO invitations (id, eventId, userId, invitationStatus, invitationRole) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.addQuery(query);
		String newId = generateNewId();

		try {
			ps.setString(1, newId);
			ps.setString(2, eventId);
			ps.setString(3, userId);
			ps.setString(4, "Waiting to be Sent");
			ps.setString(5, invitationRole);
			ps.executeUpdate();

			return new Invitation( invitationId,  eventId,  userId, "Waiting to be Sent", invitationRole);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String generateNewId() {
		String query = "SELECT MAX(id) AS maxId FROM Invitations";
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
