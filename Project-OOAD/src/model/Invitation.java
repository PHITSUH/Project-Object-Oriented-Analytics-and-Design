package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Model;

public class Invitation extends Model{

	private String invitationId;
	
	public String getInvitationId() {
		return invitationId;
	}

	public String getEventId() {
		return eventId;
	}

	public String getUserId() {
		return userId;
	}

	public String getInvitationStatus() {
		return invitationStatus;
	}

	public String getInvitationRole() {
		return invitationRole;
	}

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
	
	public String sendInvitation(String invitationId) {
		String query = "UPDATE invitation SET invitationStatus = ? WHERE id = ?";
		PreparedStatement ps = connect.addQuery(query);
		
		try {
			ps.setString(1, "Invitation Sent");
			ps.setString(2, invitationId);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return "Invitation Sent";
	}
	
	public String acceptInvitation(String invitationId) {
		String query = "UPDATE invitation SET invitationStatus = ? WHERE id = ?";
		PreparedStatement ps = connect.addQuery(query);
		
		try {
			ps.setString(1, "Invitation Accepted");
			ps.setString(2, invitationId);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return "Invitation Accepted";
	}
	
	public static ArrayList<Invitation> getInvitations(String email) {
		String query = "SELECT * FROM invitations i JOIN users u ON i.userId = u.userId WHERE userEmail = ?";
		PreparedStatement ps = connect.addQuery(query);
		ArrayList<Invitation> resArr = new ArrayList<>();
		
		try {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String invitationId = rs.getString("id");
				String eventId = rs.getString("eventId");
				String userId = rs.getString("userId");
				String invitationStatus = rs.getString("invitationStatus");
				String invitationRole = rs.getString("invitationRole");
				
				Invitation inv = new Invitation( invitationId,  eventId,  userId, invitationStatus, invitationRole);
				
				resArr.add(inv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return resArr;
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
