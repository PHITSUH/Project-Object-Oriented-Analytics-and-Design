package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product extends Model{

	String productId, vendorId, productName, productDescription;

	public Product(String productId, String vendorId, String productName, String productDescription) {
		super();
		this.productId = productId;
		this.vendorId = vendorId;
		this.productName = productName;
		this.productDescription = productDescription;
	}

	public String getProductId() {
		return productId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}
	
	public static void addProduct(User user, String productName, String productDescription) {
		String query = "INSERT INTO product (productId, vendorId, productName, productDescription) VALUES (?, ?, ?, ?, ?)";
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
