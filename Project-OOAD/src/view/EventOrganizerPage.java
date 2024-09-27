package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class EventOrganizerPage extends Page {
	int id;
	BorderPane mainPane;

	public EventOrganizerPage(Scene scene, int id) {
		super(scene);
		this.id = id;

	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setCenter(new Label("test"));

		return mainPane;
	}

	@Override
	Pane initRoot() {
		Pane root = init();
		return root;
	}

}
