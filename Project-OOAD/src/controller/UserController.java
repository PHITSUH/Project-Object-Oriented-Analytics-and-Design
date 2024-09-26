package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;
import util.Connect;

public class UserController {

	private static Connect connect = Connect.getInstance();

	public static ArrayList<User> userList = new ArrayList<>();

	public static void getUser() {
		String query = "SELECT * FROM users";
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				String email = connect.rs.getString("UserEmail");
				String username = connect.rs.getString("Username");
				int id = Integer.parseInt(connect.rs.getString("UserId"));
				String password = connect.rs.getString("UserPassword");
				String role = connect.rs.getString("UserRole");
				userList.add(new User(id, email, username, password, role));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addUser(String email, String username, String password, String role) {
		int id;
		if (userList.isEmpty()) {
			id = 1;
		} else {
			id = userList.get(userList.size() - 1).getId() + 1;
		}
		String query = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.addQuery(query);
		try {
			ps.setInt(1, id);
			ps.setString(2, email);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, role);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Alert validateRegister(String email, String username, String password, String role) {
		getUser();
		Alert alert = new Alert(AlertType.ERROR);
		if (email == null || email.isEmpty()) {
			alert.setContentText("Email must be filled");
			return alert;
		}

		if (username == null || username.isEmpty()) {
			alert.setContentText("Username must be filled");
			return alert;
		}

		if (password == null || password.isEmpty()) {
			alert.setContentText("Password must be filled");
			return alert;
		}

		if (role == null || role.isEmpty()) {
			alert.setContentText("Must choose a role");
			return alert;
		}

		for (User u : userList) {
			if (u.getUsername() == username) {
				alert.setContentText("Username must be unique");
				return alert;
			}
			if (u.getEmail() == email) {
				alert.setContentText("Email must be unique");
				return alert;
			}
		}

		addUser(email, username, password, role);

		if (password.length() < 5) {
			alert.setContentText("Password must at least be 5 letter long");
			return alert;
		}

		alert.setAlertType(AlertType.CONFIRMATION);
		alert.setContentText("Account has been successfully created!");

		return alert;
	}

}
