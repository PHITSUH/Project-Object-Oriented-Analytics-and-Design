package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;

public class AddVendorView extends Page<Event> {
	BorderPane mainPane;
	Label addVendorLabel, eventLabel;
	Button submitButton, addGuestButton, backButton;
	HBox buttonBox;
	VBox mainBox;

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.eventOrganizerNavbar());

		mainBox = new VBox();
		mainBox.setAlignment(Pos.CENTER);

		addVendorLabel = new Label("Add Vendor");
		addVendorLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));

		eventLabel = new Label("Event Name: " + data.getId());

		return mainPane;
	}

	@Override
	Pane initRoot() {
		Pane root = init();
		return root;
	}

}
