package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.InvitationController;

public class Vendor extends User {

	private ArrayList<Invitation> acceptedInvitations; 
	
	public Vendor(String id, String email, String name, String password, String role) {
		super(id, email, name, password, role);
		acceptedInvitations = new ArrayList<>();
	}

	public static List<User> getAllVendors() {
		String query = "SELECT * FROM User WHERE UserRole LIKE 'Vendor'";
		connect.rs = connect.execQuery(query);
		List<User> userList = new ArrayList<>();
		try {
			while (connect.rs.next()) {
				String userId = connect.rs.getString("userId");
				String userName = connect.rs.getString("userName");
				String userEmail = connect.rs.getString("userEmail");
				String userPassword = connect.rs.getString("userPassword");
				String userRole = connect.rs.getString("userRole");
				userList.add(new Vendor(userId, userEmail, userName, userPassword, userRole));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	public static List<User> getVendorsByTransactionID(String eventID) {
		String query = "SELECT UserId FROM Invitation WHERE EventId LIKE ? AND InvitationRole "
				+ "LIKE 'Vendor' AND InvitationStatus LIKE 'Accepted'";
		PreparedStatement ps = connect.addQuery(query);

		List<User> vendorList = new ArrayList<>();
		try {
			ps.setString(1, eventID);
			connect.rs = ps.executeQuery();
			while (connect.rs.next()) {
				String userId = connect.rs.getString("userId");
				vendorList.add(getVendorById(userId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vendorList;
	}

	public static Vendor getVendorById(String id) {
		String query = "SELECT * FROM User WHERE UserId LIKE ?";
		PreparedStatement ps = connect.addQuery(query);

		try {
			connect.rs = ps.executeQuery();
			String email = connect.rs.getString("UserEmail");
			String name = connect.rs.getString("UserName");
			String pass = connect.rs.getString("UserPassword");
			String role = connect.rs.getString("UserPassword");
			return new Vendor(id, email, name, pass, role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	public String acceptInvitation(String InvitationId) {
		return InvitationController.acceptInvitation(InvitationId);
	}
}
