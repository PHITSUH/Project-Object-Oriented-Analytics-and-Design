package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class EventOrganizerPage {
	int id;
	BorderPane mainPane;

	public EventOrganizerPage(Scene scene, int id) {
		new ViewEventPage(scene, id).show();

	}

}
