package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;

public class User {
	int id;
	String email, username, password, role;
	private static Connect connect = Connect.getInstance();

	public User(int id, String email, String username, String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static ArrayList<User> getUser() {
		ArrayList<User> userList = new ArrayList<>();
		String query = "SELECT * FROM users";
		connect.rs = connect.execQuery(query);
		userList = new ArrayList<>();
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

		return userList;
	}

	public static void addUser(String email, String username, String password, String role) {
		int id;
		ArrayList<User> userList = getUser();
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

}
