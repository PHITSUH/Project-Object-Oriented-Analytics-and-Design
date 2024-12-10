package controller;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

public class VendorController extends Controller {

	public static List<User> filterVendor(List<User> participants) {
		return participants.stream().filter((vendor) -> vendor.getRole().equals("Vendor")).collect(Collectors.toList());
	}

	public static TableView<User> createTableView(List<User> vendorList) {
		TableView<User> tableView = new TableView<>();

		if (vendorList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<User, Integer> vendorIdColumn = new TableColumn<>("Vendor Id");
			vendorIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			vendorIdColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			TableColumn<User, String> vendorNameColumn = new TableColumn<>("Vendor Name");
			vendorNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			vendorNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			TableColumn<User, String> vendorEmailColumn = new TableColumn<>("Vendor Email");
			vendorEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
			vendorEmailColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			tableView.getColumns().add(vendorIdColumn);
			tableView.getColumns().add(vendorNameColumn);
			tableView.getColumns().add(vendorEmailColumn);

			ObservableList<User> vendorObsList = FXCollections.observableArrayList(vendorList);

			tableView.setItems(vendorObsList);
		}

		return tableView;
	}
}
