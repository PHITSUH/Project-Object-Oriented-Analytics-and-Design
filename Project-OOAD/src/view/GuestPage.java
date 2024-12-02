package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GuestPage extends Page {
	int id;

	public GuestPage(Scene scene, int id) {
		super(scene);
		this.id = id;
	}

	@Override
	Pane initRoot() {
		return null;
	}

}
