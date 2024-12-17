package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Admin;
import model.Event;
import model.Guest;
import model.User;
import model.Vendor;
import util.Result;
import view.EventDetailsPage;
import view.EventDetailsPage.Props;
import view.ViewEventPage;
import view.ViewUserPage;

public class AdminController extends Controller {
	public static void getAllEvents() {

	}

	public static void getAllUsers() {

	}

	public static void getGuestsByTransactionID(String eventId) {

	}

	public static void getVendorsByTransactionID(String eventId) {

	}

	public static TableView<User> createTableView(List<User> userList) {
		TableView<User> tableView = new TableView<>();

		if (userList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<User, String> userNameColumn = new TableColumn<>("User Name");
			userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			userNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			TableColumn<User, String> userEmailColumn = new TableColumn<>("User Email");
			userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
			userEmailColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			TableColumn<User, String> userRoleColumn = new TableColumn<>("User Role");
			userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
			userRoleColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			tableView.getColumns().add(userNameColumn);
			tableView.getColumns().add(userEmailColumn);
			tableView.getColumns().add(userRoleColumn);

			ObservableList<User> userObsList = FXCollections.observableArrayList(userList);

			tableView.setItems(userObsList);
		}

		return tableView;
	}

	public static Result<Void, String> userSelected(User selectedUser) {
		if (selectedUser == null) {
			return Result.err("Select a User First!");
		}
		return Result.ok(null);
	}

	public static void viewAllUsers() {
		navigate(new ViewUserPage(), User.getAllUsers());
	}

	public static void viewAllEvents() {
		navigate(new ViewEventPage(), Admin.getAllEvents());
	}

	public static void viewEventDetails(String eventId) {
		List<User> participantList = new ArrayList<>();
		participantList.addAll(Guest.getGuestsByTransactionID(eventId));
		participantList.addAll(Vendor.getVendorsByTransactionID(eventId));
		navigate(new EventDetailsPage(), new Props(Event.getEventById(eventId), participantList));
	}

	public static void deleteEvent(String eventId) {
		Event.deleteEvent(eventId);
	}

	public static void deleteUser(String userId) {
		User.deleteUser(userId);
	}

}
