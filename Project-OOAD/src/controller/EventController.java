package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Event;

public class EventController {

	public static TableView<Event> createTableView(List<Event> eventList) {
		TableView<Event> tableView = new TableView<>();

		if (eventList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<Event, Integer> eventIdColumn = new TableColumn<>("Event Id");
			eventIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			eventIdColumn.prefWidthProperty().bind(tableView.widthProperty().divide(4));

			TableColumn<Event, String> eventNameColumn = new TableColumn<>("Event Name");
			eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			eventNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(4));

			TableColumn<Event, String> eventDateColumn = new TableColumn<>("Event Date");
			eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
			eventDateColumn.prefWidthProperty().bind(tableView.widthProperty().divide(4));

			TableColumn<Event, String> eventLocationColumn = new TableColumn<>("Event Location");
			eventLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
			eventLocationColumn.prefWidthProperty().bind(tableView.widthProperty().divide(4));

			tableView.getColumns().add(eventIdColumn);
			tableView.getColumns().add(eventNameColumn);
			tableView.getColumns().add(eventDateColumn);
			tableView.getColumns().add(eventLocationColumn);

			ObservableList<Event> eventObsList = FXCollections.observableArrayList(eventList);

			tableView.setItems(eventObsList);
		}

		return tableView;
	}

}
