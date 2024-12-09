package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Guest extends User {
	List<Event> eventAccepted;

	public Guest(String id, String email, String name, String password, String role) {
		super(id, email, name, password, role);
	}

	public static List<User> getGuestsByTransactionID(String eventID) {
		String query = "SELECT User.* FROM Invitation JOIN User ON Invitation.UserId = User.UserId WHERE EventId LIKE '"
				+ eventID + "' AND InvitationRole LIKE 'Guest' AND InvitationStatus LIKE 'Accepted'";
		connect.rs = connect.execQuery(query);
		List<User> guestList = new ArrayList<>();
		try {
			while (connect.rs.next()) {
				String userId = connect.rs.getString("userId");
				String email = connect.rs.getString("UserEmail");
				String name = connect.rs.getString("UserName");
				String pass = connect.rs.getString("UserPassword");
				String role = connect.rs.getString("UserPassword");

				guestList.add(new Guest(userId, email, name, pass, role));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return guestList;
	}

}
