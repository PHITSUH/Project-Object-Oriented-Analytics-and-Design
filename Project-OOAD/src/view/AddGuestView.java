package view;

import java.util.List;

import controller.EventOrganizerController;
import controller.GuestController;
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
import model.Invitation;
import model.User;
import util.Result;

public class AddGuestView extends Page<view.AddGuestView.Props> {
	public static class Props {
		protected Event event;
		protected List<User> guestList;

		public Props(Event event, List<User> guestList) {
			super();
			this.event = event;
			this.guestList = guestList;
		}
	}

	BorderPane mainPane;
	VBox mainBox;
	Label addGuestLabel, eventLabel;
	TableView<User> guestView;
	Button backButton, submitButton, addVendorButton;
	HBox buttonBox;

	public void event() {
		backButton.setOnAction(e -> {
			EventOrganizerController.viewOrganizedEvents();
			return;
		});

		submitButton.setOnAction(e -> {
			User selectedGuest = guestView.getSelectionModel().getSelectedItem();
			Result<Void, String> result = EventOrganizerController.checkAddGuestInput(selectedGuest,
					data.event.getId());
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}
			Invitation.sendInvitation(data.event, selectedGuest);

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Guest has been invited to the event!");
			alert.showAndWait();
			EventOrganizerController.viewOrganizedEvents();
			return;
		});

		addVendorButton.setOnAction(e -> {
			EventOrganizerController.viewAddVendor(data.event);
		});
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.eventOrganizerNavbar());

		mainBox = new VBox();
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPadding(new Insets(20));

		addGuestLabel = new Label("Add Guest");
		addGuestLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));

		eventLabel = new Label("Event Name: " + data.event.getName());
		mainBox.getChildren().addAll(addGuestLabel, eventLabel);

		if (data.guestList.isEmpty())
			mainBox.getChildren().add(new Label("There are no guests yet in the database..."));
		else {
			guestView = GuestController.createTableView(data.guestList);
			mainBox.getChildren().add(guestView);
		}

		buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10));

		submitButton = new Button("Add Guest");
		submitButton.setPrefWidth(200);

		backButton = new Button("Go Back");
		backButton.setPrefWidth(200);

		addVendorButton = new Button("Go to Add Vendor Page");
		addVendorButton.setPrefWidth(200);

		buttonBox.getChildren().addAll(backButton, submitButton, addVendorButton);

		mainBox.getChildren().add(buttonBox);

		mainPane.setCenter(mainBox);
		event();

		return mainPane;
	}

	@Override
	Pane initRoot() {
		// TODO Auto-generated method stub
		root = init();
		return root;
	}
}
