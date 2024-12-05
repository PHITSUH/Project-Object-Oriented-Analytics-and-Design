package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import util.Result;

public abstract class User extends Model {
	// class diagram suruh ID -> String
	private String id;
	private String email;
	private String name;
	private String password;
	private String role;

	private static User currentUser = null;

	public static final String GUEST_ROLE = "Guest";
	public static final String VENDOR_ROLE = "Vendor";
	public static final String ADMIN_ROLE = "Admin";
	public static final String EVENT_ORGANIZER_ROLE = "Event Organizer";

	protected User(String id, String email, String name, String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	protected static List<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<>();
		String query = "SELECT * FROM users";
		connect.rs = connect.execQuery(query);
		userList = new ArrayList<>();
		try {
			while (connect.rs.next()) {
				String email = connect.rs.getString("UserEmail");
				String username = connect.rs.getString("UserName");
				String id = connect.rs.getString("UserId");
				String password = connect.rs.getString("UserPassword");
				String role = connect.rs.getString("UserRole");

				if (role.equals(EVENT_ORGANIZER_ROLE)) {
					userList.add(new EventOrganizer(id, email, username, password, role));
				} else if (role.equals(VENDOR_ROLE)) {
					userList.add(new Vendor(id, email, username, password, role));
				} else if (role.equals(ADMIN_ROLE)) {
					userList.add(new Admin(id, email, username, password, role));
				} else {
					userList.add(new Guest(id, email, username, password, role));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	public static void register(String email, String username, String password, String role) {
		int maxId = 1;
		List<User> userList = getAllUsers();
		// karena bisa delete user, id terbesar ga selalu sesuai dengan size list
		// cari manual
		// asumsi format ID -> XXXXX (Soal suruh pake String tapi ga dikasih format)
		for (User user : userList) {
			// kita mau ambil id yang selanjutnya, jadi + 1
			int nextId = Integer.parseInt(user.id) + 1;
			maxId = Math.max(maxId, nextId);
		}

		String query = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.addQuery(query);
		try {
			ps.setString(1, String.format("%05d", maxId));
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

	public static Result<User, String> login(String email, String password) {
		User user = getAllUsers().stream().filter((u) -> u.email.equals(email) && u.password.equals(password))
				.findFirst().orElse(null);
		if (user == null) {
			return Result.err("Invalid credentials!");
		}
		currentUser = user;
		return Result.ok(user);
	}

	public static Optional<User> getUserByEmail(String email) {
		return getAllUsers().stream().filter((user) -> user.email.equals(email)).findFirst();
	}

	// getUserByUsername -> getUserByName
	// karena penamaan variablenya semua pake name bukan username
	// lebih konsisten kalo tetep pake Name dibandingkan Username
	// kode ga konsisten = kode yang gampang salah
	public static Optional<User> getUserByName(String name) {
		return getAllUsers().stream().filter((user) -> user.name.equals(name)).findFirst();
	}

	// di class diagram ditulis User ada checkRegisterInput
	// tapi kalau di Sequence diagram,
	// checkRegisterInput hanya ada di UserController
	// menurut kami lebih masuk akal kalau validasi di Controller bukan di model
//	public static String checkRegisterInput(String email, String name, String password) {
//		
//	}

	public Result<Void, String> checkChangeProfileInput(String email, String name, String oldPassword,
			String newPassword) {
		if (!this.password.equals(oldPassword)) {
			return Result.err("Old password is not the same as current password");
		}
		if (newPassword.length() < 5) {
			return Result.err("New password must be at least 5 characters long");
		}

		// dari soal dibilang:
		// users are not required to change all fields simultaneously
		// they can also change only one field or all fields
		int changed = 0;
		if (!this.password.equals(newPassword)) {
			changed++;
		}
		if (!this.email.equals(email)) {
			changed++;
		}
		if (!this.name.equals(name)) {
			changed++;
		}
		if (changed < 1) {
			return Result.err("You must change at least one field!");
		}

		return Result.ok(null);
	}

	public void changeProfile(String email, String name, String oldPassword, String newPassword) {
		String query = "UPDATE users SET Email = ? , Name = ?, Password = ? WHERE Id = ?";
		PreparedStatement ps = connect.addQuery(query);
		try {
			ps.setString(1, email);
			ps.setString(2, name);
			ps.setString(3, newPassword);
			ps.setString(4, this.id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
