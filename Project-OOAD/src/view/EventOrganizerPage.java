package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class EventOrganizerPage {
	int id;
	private BorderPane mainPane;

	public EventOrganizerPage(Scene scene, int id) {
		new ViewEventPage(scene, id).show();

	}

}
