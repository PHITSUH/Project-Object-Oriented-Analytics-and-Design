package view;

import java.util.ArrayList;

import controller.EventController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;

public class ViewEventPage extends Page {
	int id;
	BorderPane mainPane;
	TableView<Event> tableView;
	ArrayList<Event> eventList;
	Label viewLabel, emptyTableLabel;
	VBox mainBox;

	public ViewEventPage(Scene scene, int id) {
		super(scene);
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.eventOrganizerNavbar(scene, id));
		eventList = Event.getEventByUserId(id);
		viewLabel = new Label("Events");
		viewLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));
		mainBox = new VBox();
		mainBox.getChildren().add(viewLabel);
		mainBox.setAlignment(Pos.CENTER);

		tableView = EventController.createTableView(eventList);
		if (tableView == null) {
			emptyTableLabel = new Label("You don't have any event yet, create an event now.");
			mainBox.getChildren().add(emptyTableLabel);
		} else {
			mainBox.getChildren().add(tableView);
		}

		mainPane.setCenter(mainBox);
		return mainPane;
	}

	@Override
	Pane initRoot() {
		// TODO Auto-generated method stub
		Pane root = init();
		return root;
	}

}
