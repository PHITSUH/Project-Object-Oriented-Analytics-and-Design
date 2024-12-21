package view;

import java.util.List;

import controller.EventOrganizerController;
import controller.VendorController;
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

public class AddVendorPage extends Page<AddVendorPage.Props> {
	public static class Props {
		protected Event event;
		protected List<User> vendorList;

		public Props(Event event, List<User> vendorList) {
			super();
			this.event = event;
			this.vendorList = vendorList;
		}
	}

	private BorderPane mainPane;
	private Label addVendorLabel, eventLabel, emptyLabel;
	private Button submitButton, addGuestButton, backButton;
	private HBox buttonBox;
	private VBox mainBox;
	private TableView<User> tableView;

	public void event() {
		submitButton.setOnAction(e -> {
			User selectedVendor = tableView.getSelectionModel().getSelectedItem();
			Result<Void, String> result = EventOrganizerController.checkAddVendorInput(selectedVendor,
					data.event.getId());
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}
			Invitation.sendInvitation(data.event, selectedVendor);

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Vendor has been invited to the event!");
			alert.showAndWait();
			EventOrganizerController.viewOrganizedEvents();
			return;
		});

		backButton.setOnAction(e -> {
			EventOrganizerController.viewOrganizedEvents();
			return;
		});

		addGuestButton.setOnAction(e -> {
			EventOrganizerController.viewAddGuest(data.event);
		});
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.eventOrganizerNavbar());

		mainBox = new VBox();
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPadding(new Insets(20));

		addVendorLabel = new Label("Add Vendor");
		addVendorLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));

		eventLabel = new Label("Event Name: " + data.event.getName());

		if (data.vendorList.isEmpty()) {
			emptyLabel = new Label("There are no vendors available in the database...");
			mainBox.getChildren().addAll(addVendorLabel, emptyLabel);
		} else {
			tableView = VendorController.createTableView(data.vendorList);
			mainBox.getChildren().addAll(addVendorLabel, eventLabel, tableView);
		}

		buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(10));

		submitButton = new Button("Add Vendor");
		submitButton.setPrefWidth(200);

		addGuestButton = new Button("Go to Add Guest");
		addGuestButton.setPrefWidth(200);

		backButton = new Button("Go Back");
		backButton.setPrefWidth(200);

		buttonBox.getChildren().addAll(backButton, submitButton, addGuestButton);

		mainBox.getChildren().add(buttonBox);

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
