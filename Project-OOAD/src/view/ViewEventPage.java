package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;

public class ViewEventPage extends Page<Void> {
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

		// if there is no event, don't show a table
		// show a label saying "no events"
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
