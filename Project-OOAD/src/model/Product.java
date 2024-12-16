package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product extends Model {

	String productId, vendorId, name, description;

	public Product(String productId, String vendorId, String productName, String productDescription) {
		super();
		this.productId = productId;
		this.vendorId = vendorId;
		this.name = productName;
		this.description = productDescription;
	}

	public String getProductId() {
		return productId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public static void addProduct(User user, String productName, String productDescription) {
		String query = "INSERT INTO product (productId, vendorId, productName, productDescription) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = connect.addQuery(query);
		String newId = generateNewId();

		try {
			ps.setString(1, newId);
			ps.setString(2, user.getId());
			ps.setString(3, productName);
			ps.setString(4, productDescription);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Product> getVendorProduct(String userId) {
		String query = "SELECT * FROM product WHERE VendorId LIKE ?";
		PreparedStatement ps = connect.addQuery(query);
		List<Product> productList = new ArrayList<>();
		try {
			ps.setString(1, userId);
			connect.rs = ps.executeQuery();
			while (connect.rs.next()) {
				String productId = connect.rs.getString("ProductId");
				String name = connect.rs.getString("ProductName");
				String desc = connect.rs.getString("ProductDescription");
				productList.add(new Product(productId, userId, name, desc));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}

	private static String generateNewId() {
		String query = "SELECT MAX(productId) AS maxId FROM Product";
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
