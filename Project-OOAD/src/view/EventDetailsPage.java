package view;

import java.util.List;

import controller.EventOrganizerController;
import controller.GuestController;
import controller.VendorController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import model.User;

public class EventDetailsPage extends Page<EventDetailsPage.Props> {
	public static class Props {
		protected Event event;
		protected List<User> participantList;

		public Props(Event event, List<User> participants) {
			super();
			this.event = event;
			this.participantList = participants;
		}
	}

	BorderPane mainPane;
	VBox mainBox;
	Label titleLabel, nameLabel, dateLabel, locationLabel, descLabel;
	TableView<User> vendorView, guestView;
	Button backButton;
	HBox tableBox;

	public void event() {
		backButton.setOnAction(e -> {
			EventOrganizerController.viewOrganizedEvents();
			return;
		});
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.roleNavbar());

		mainBox = new VBox(20);
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPadding(new Insets(10));

		titleLabel = new Label("Event Details");
		titleLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));

		nameLabel = new Label("Event Name: " + data.event.getName());

		dateLabel = new Label("Event Date: " + data.event.getDate());

		locationLabel = new Label("Event Location: " + data.event.getLocation());

		descLabel = new Label("Desc Label: " + data.event.getDescription());

		mainBox.getChildren().addAll(titleLabel, nameLabel, dateLabel, locationLabel, descLabel);
		mainBox.setMargin(titleLabel, new Insets(0, 0, 20, 0));

		if (!data.participantList.isEmpty()) {
			tableBox = new HBox();
			tableBox.setAlignment(Pos.CENTER);
			tableBox.setPadding(new Insets(20));

			if (!VendorController.filterVendor(data.participantList).isEmpty()) {
				vendorView = VendorController.createTableView(VendorController.filterVendor(data.participantList));

				tableBox.getChildren().add(vendorView);
			} else
				tableBox.getChildren().add(new Label("This Event does not have any vendor"));

			if (!GuestController.filterGuest(data.participantList).isEmpty()) {
				guestView = GuestController.createTableView(GuestController.filterGuest(data.participantList));

				tableBox.getChildren().add(guestView);
			} else
				tableBox.getChildren().add(new Label("This Event does not have any guest"));

			mainBox.getChildren().add(tableBox);
		} else
			mainBox.getChildren().add(new Label("This Event does not have any guest or vendor"));

		backButton = new Button("Go Back");

		mainBox.getChildren().add(backButton);

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
