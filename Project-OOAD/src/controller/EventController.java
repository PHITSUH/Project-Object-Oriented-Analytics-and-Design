package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Event;

public class EventController {

	public static TableView<Event> createTableView(ArrayList<Event> eventList) {
		TableView<Event> tableView = new TableView<>();

		if (eventList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<Event, Integer> eventIdColumn = new TableColumn<>("Event Id");
			eventIdColumn.setCellValueFactory(new PropertyValueFactory<>("EventId"));

			TableColumn<Event, String> eventNameColumn = new TableColumn<>("Event Name");
			eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));

			TableColumn<Event, String> eventDateColumn = new TableColumn<>("Event Date");
			eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("EventDate"));

			TableColumn<Event, String> eventLocationColumn = new TableColumn<>("Event Location");
			eventLocationColumn.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));

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
