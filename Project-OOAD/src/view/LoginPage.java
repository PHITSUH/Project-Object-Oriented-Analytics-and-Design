package view;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class LoginPage extends Page {

	public LoginPage(Scene scene) {
		super(scene);
	}

	BorderPane mainPane;

	@Override
	Pane initRoot() {
		mainPane = new BorderPane();
		MenuBar navigationBar = ComponentFactory.landingNavbar(scene);
		mainPane.setTop(navigationBar);

		return mainPane;
	}

}
