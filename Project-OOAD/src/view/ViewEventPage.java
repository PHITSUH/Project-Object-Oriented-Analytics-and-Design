package view;

import java.util.List;

import controller.EventController;
import controller.EventOrganizerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;
import util.Result;

public class ViewEventPage extends Page<List<Event>> {

	private BorderPane mainPane;
	private TableView<Event> tableView;
	private Label viewLabel, emptyTableLabel;
	private VBox mainBox;
	private HBox buttonBox;
	private Button addVendorButton, addGuestButton, viewDetailButton, changeEventButton;

	public void event() {
		addVendorButton.setOnAction(e -> {
			Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
			Result<Void, String> result = EventOrganizerController.eventSelected(selectedEvent);
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}

			EventOrganizerController.viewAddVendor(selectedEvent);
		});

		addGuestButton.setOnAction(e -> {
			Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
			Result<Void, String> result = EventOrganizerController.eventSelected(selectedEvent);
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}
			EventOrganizerController.viewAddGuest(selectedEvent);
		});

		viewDetailButton.setOnAction(e -> {
			Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
			Result<Void, String> result = EventOrganizerController.eventSelected(selectedEvent);
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}
			EventOrganizerController.viewOrganizedEventDetails(selectedEvent);

		});

		changeEventButton.setOnAction(e -> {
			Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
			Result<Void, String> result = EventOrganizerController.eventSelected(selectedEvent);
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}

			EventOrganizerController.viewChangeEventPage(selectedEvent);
		});
	}

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

		buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10));

		addVendorButton = new Button("Add Vendor");
		addVendorButton.setPrefWidth(200);

		addGuestButton = new Button("Add Guest");
		addGuestButton.setPrefWidth(200);

		viewDetailButton = new Button("View Event Detail");
		viewDetailButton.setPrefWidth(200);

		changeEventButton = new Button("Change Event Name");
		changeEventButton.setPrefWidth(200);

		buttonBox.getChildren().addAll(addVendorButton, addGuestButton, viewDetailButton, changeEventButton);

		mainBox.getChildren().add(buttonBox);

		mainPane.setCenter(mainBox);

		event();
		return mainPane;
	}

	@Override
	Pane initRoot() {
		// TODO Auto-generated method stub
		Pane root = init();

		return root;
	}

}
