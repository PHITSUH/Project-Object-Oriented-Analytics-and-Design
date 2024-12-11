package view;

import controller.EventOrganizerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;
import util.Result;

public class ChangeEventPage extends Page<Event> {

	BorderPane mainPane;
	VBox mainBox;
	HBox textBox;
	Label nameLabel, titleLabel;
	TextField nameField;
	Button submitButton;

	public void event() {
		submitButton.setOnAction(e -> {
			Result<Void, String> result = EventOrganizerController.checkEditEventInput(nameField.getText(), data);
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}

			Event.editEventName(nameField.getText(), data.getId());
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Event's Name has been changed!");
			alert.showAndWait();
			closePopup();
			EventOrganizerController.viewOrganizedEvents();
		});
	}

	public Pane init() {
		mainPane = new BorderPane();

		mainBox = new VBox(30);
		mainBox.setAlignment(Pos.CENTER);

		textBox = new HBox();
		textBox.setAlignment(Pos.CENTER);

		titleLabel = new Label("Change Event Name");
		titleLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 30));

		nameLabel = new Label("Event: ");

		nameField = new TextField();
		nameField.setText(data.getName());
		nameField.setPrefWidth(250);

		textBox.getChildren().addAll(nameLabel, nameField);
		textBox.setMargin(nameLabel, new Insets(0, 20, 0, 0));

		submitButton = new Button("Change Event Name");
		submitButton.setPrefWidth(200);

		mainBox.getChildren().addAll(titleLabel, textBox, submitButton);

		mainPane.setCenter(mainBox);
		event();

		return mainPane;
	}

	@Override
	Pane initRoot() {
		this.root = init();
		return root;
	}

}
