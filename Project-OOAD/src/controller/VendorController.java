package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.User;
import model.Vendor;
import util.Result;

public class VendorController extends Controller {

	public static List<User> filterVendor(List<User> participants) {
		List<User> vendorList = new ArrayList<>();
		for (User u : participants)
			if (u instanceof Vendor)
				vendorList.add(u);
		return vendorList;
	}

	public static TableView<User> createTableView(List<User> vendorList) {
		TableView<User> tableView = new TableView<>();

		if (vendorList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<User, String> vendorNameColumn = new TableColumn<>("Vendor Name");
			vendorNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			vendorNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2));

			TableColumn<User, String> vendorEmailColumn = new TableColumn<>("Vendor Email");
			vendorEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
			vendorEmailColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2));

			tableView.getColumns().add(vendorNameColumn);
			tableView.getColumns().add(vendorEmailColumn);

			ObservableList<User> vendorObsList = FXCollections.observableArrayList(vendorList);

			tableView.setItems(vendorObsList);
		}

		return tableView;
	}
	
	public static Result<String, String> manageVendor(User user, String name, String desc) {
		
		if(name.isEmpty()) {
			return Result.err("Name cannot be empty");
		}else if(desc.isEmpty()) {
			return Result.err("Description cannot be empty");
		}else if(desc.length() > 50) {
			return Result.err("Descroption must not be more than 50 Characters");
		}
		Vendor.manageVendor(user, name, desc);
		return Result.ok("Product Successfully Added");
	}
}
