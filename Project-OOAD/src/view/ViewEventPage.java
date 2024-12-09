package view;

import java.util.List;

import controller.EventController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;

public class ViewEventPage extends Page<List<Event>> {

	private BorderPane mainPane;
	private TableView<Event> tableView;
	private Label viewLabel, emptyTableLabel;
	private VBox mainBox;

	public Pane init() {

		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.eventOrganizerNavbar());
		viewLabel = new Label("Events");
		viewLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));
		mainBox = new VBox();
		mainBox.getChildren().add(viewLabel);
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPadding(new Insets(20));

		// if there is no event, don't show a table
		// show a label saying "no events"
		// ini kita yang komen btw bkn gpt ato ai bot just saying

		if (data == null || data.isEmpty()) {
			emptyTableLabel = new Label("You don't have any event yet, create an event now.");
			mainBox.getChildren().add(emptyTableLabel);
		} else {
			tableView = EventController.createTableView(data);
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
