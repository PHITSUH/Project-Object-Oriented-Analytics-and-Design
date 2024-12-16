package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.InvitationController;
import controller.ProductController;

public class Vendor extends User {

	private List<Invitation> acceptedInvitations;

	public Vendor(String id, String email, String name, String password, String role) {
		super(id, email, name, password, role);
		acceptedInvitations = new ArrayList<>(InvitationController.getInvitationsByEmail(email));
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
		String query = "SELECT User.* FROM Invitation JOIN User ON Invitation.UserId = User.UserId WHERE EventId LIKE '"
				+ eventID + "' AND InvitationRole LIKE 'Vendor' AND InvitationStatus LIKE 'Accepted'";

		List<User> vendorList = new ArrayList<>();
		try {
			connect.rs = connect.execQuery(query);
			while (connect.rs.next()) {
				String userId = connect.rs.getString("userId");
				String email = connect.rs.getString("UserEmail");
				String name = connect.rs.getString("UserName");
				String pass = connect.rs.getString("UserPassword");
				String role = connect.rs.getString("UserPassword");

				vendorList.add(new Vendor(userId, email, name, pass, role));
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
			ps.setString(1, id);
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

	public List<Invitation> viewAcceptedEvents() {
		reloadAccepted();
		return acceptedInvitations;
	}

	public String acceptInvitation(String InvitationId) {
		return InvitationController.acceptInvitation(InvitationId);
	}

	public void reloadAccepted() {
		acceptedInvitations.clear();
		acceptedInvitations.addAll(InvitationController.getInvitationsByEmail(this.getEmail()));
	}

	private void checkManageVendor(String desc, Product product) {
		// TODO Auto-generated method stub

	}

	public static void manageVendor(User user, String name, String desc) {
		ProductController.addProduct(user, name, desc);
	}
}
