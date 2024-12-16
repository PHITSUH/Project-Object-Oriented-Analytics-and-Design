package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Event;
import model.Guest;
import model.User;
import view.ViewEventPage;

public class GuestController extends Controller {

	public static List<User> filterGuest(List<User> participants) {
		List<User> guestList = new ArrayList<>();
		for (User u : participants)
			if (u instanceof Guest)
				guestList.add(u);

		return guestList;
	}

	public static void viewOrganizedEvents() {
		// fetch shit here
		List<Event> eventList = Event.getAcceptedEvents();
		navigate(new ViewEventPage(), eventList);
	}

	public static TableView<User> createTableView(List<User> guestList) {
		TableView<User> tableView = new TableView<>();

		if (guestList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<User, String> guestNameColumn = new TableColumn<>("Guest Name");
			guestNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			guestNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2));

			TableColumn<User, String> guestEmailColumn = new TableColumn<>("Guest Email");
			guestEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
			guestEmailColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2));

			tableView.getColumns().add(guestNameColumn);
			tableView.getColumns().add(guestEmailColumn);

			ObservableList<User> guestObsList = FXCollections.observableArrayList(guestList);

			tableView.setItems(guestObsList);
		}

		return tableView;
	}

}
