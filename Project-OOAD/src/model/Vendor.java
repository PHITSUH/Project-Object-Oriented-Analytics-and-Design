package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Vendor extends User {

	public Vendor(String id, String email, String name, String password, String role) {
		super(id, email, name, password, role);
	}

	public static List<User> getVendorsByTransactionID(String eventID) {
		String query = "SELECT UserId FROM Invitation WHERE EventId LIKE '" + eventID
				+ "' AND InvitationRole LIKE 'Vendor' AND InvitationStatus LIKE 'Accepted'";
		connect.rs = connect.execQuery(query);
		List<User> vendorList = new ArrayList<>();
		try {
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
		String query = "SELECT * FROM User WHERE UserId LIKE '" + id + "'";
		connect.rs = connect.execQuery(query);
		try {
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
}
