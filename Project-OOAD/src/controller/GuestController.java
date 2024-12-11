package controller;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

public class GuestController extends Controller {

	public static List<User> filterGuest(List<User> participants) {
		return participants.stream().filter((guest) -> guest.getRole().equals("Guest")).collect(Collectors.toList());
	}

	public static TableView<User> createTableView(List<User> guestList) {
		TableView<User> tableView = new TableView<>();

		if (guestList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<User, Integer> guestIdColumn = new TableColumn<>("Guest Id");
			guestIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			guestIdColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			TableColumn<User, String> guestNameColumn = new TableColumn<>("Guest Name");
			guestNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			guestNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			TableColumn<User, String> guestEmailColumn = new TableColumn<>("Guest Email");
			guestEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
			guestEmailColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

			tableView.getColumns().add(guestIdColumn);
			tableView.getColumns().add(guestNameColumn);
			tableView.getColumns().add(guestEmailColumn);

			ObservableList<User> guestObsList = FXCollections.observableArrayList(guestList);

			tableView.setItems(guestObsList);
		}

		return tableView;
	}
}
