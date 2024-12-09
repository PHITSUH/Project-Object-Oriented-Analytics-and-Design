package main;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.LoginPage;
import view.Page;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene primaryScene = new Scene(new Pane(), 800, 500);

		Page<Void> page = new LoginPage();
		page.show(primaryScene);

		primaryStage.setScene(primaryScene);
		Controller.initialize(primaryStage.getScene());
		primaryStage.show();
	}
}