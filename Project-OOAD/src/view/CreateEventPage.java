package view;

import controller.EventOrganizerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;
import util.Result;

public class CreateEventPage extends Page<Void> {
	private BorderPane mainPane;
	private TextField nameField, locationField, descField;
	private DatePicker datePicker;
	private Label nameLabel, dateLabel, locationLabel, descLabel, createLabel;
	private VBox mainBox, nameBox, dateBox, locationBox, descBox;
	private Button submitButton;

	public void event() {
		submitButton.setOnAction(e -> {
			System.out.println("test");
			Result<Void, String> createEvent = EventOrganizerController.createEvent(nameField.getText(),
					datePicker.getValue(), locationField.getText(), descField.getText(), User.getCurrentUser().getId());
			if (createEvent.isErr()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText(createEvent.getError());
				alert.show();
				return;
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Event Created!");
				alert.show();
			}

		});
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.eventOrganizerNavbar());

		createLabel = new Label("Create Event");
		createLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));
		nameLabel = new Label("Event Name");
		dateLabel = new Label("Event Date");
		locationLabel = new Label("Event Location");
		descLabel = new Label("Event Description");

		nameField = new TextField();
		nameField.setPromptText("Event Name");
		nameField.setMaxWidth(200);

		datePicker = new DatePicker();
		datePicker.setPrefWidth(200);

		locationField = new TextField();
		locationField.setPromptText("Event Location");
		locationField.setMaxWidth(200);

		descField = new TextField();
		descField.setPromptText("Event Description");
		descField.setMaxWidth(200);

		nameBox = new VBox();
		nameBox.getChildren().addAll(nameLabel, nameField);
		nameBox.setAlignment(Pos.CENTER);
		VBox.setMargin(nameField, new Insets(0, 0, 20, 0));

		dateBox = new VBox();
		dateBox.getChildren().addAll(dateLabel, datePicker);
		dateBox.setAlignment(Pos.CENTER);
		VBox.setMargin(datePicker, new Insets(0, 0, 20, 0));

		locationBox = new VBox();
		locationBox.getChildren().addAll(locationLabel, locationField);
		locationBox.setAlignment(Pos.CENTER);
		VBox.setMargin(locationField, new Insets(0, 0, 20, 0));

		descBox = new VBox();
		descBox.getChildren().addAll(descLabel, descField);
		descBox.setAlignment(Pos.CENTER);
		VBox.setMargin(descField, new Insets(0, 0, 20, 0));

		submitButton = new Button("Submit");

		mainBox = new VBox();
		mainBox.getChildren().addAll(createLabel, nameBox, dateBox, locationBox, descBox, submitButton);
		mainBox.setAlignment(Pos.CENTER);
		mainPane.setCenter(mainBox);
		event();

		return mainPane;
	}

	@Override
	Pane initRoot() {
		Pane root = init();
		return root;
	}

}
