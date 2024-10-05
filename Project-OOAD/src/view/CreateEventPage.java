package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CreateEventPage extends Page {
	int id;
	BorderPane mainPane;

	public CreateEventPage(Scene scene, int id) {
		super(scene);
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.eventOrganizerNavbar(scene, id));

		return mainPane;
	}

	@Override
	Pane initRoot() {
		// TODO Auto-generated method stub
		Pane root = init();
		return root;
	}

}
