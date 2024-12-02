package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.User;

public class ComponentFactory {
	static MenuBar landingNavbar(Scene scene) {
		MenuBar menuBar = new MenuBar();
		Menu loginMenu = new Menu("Actions");
		menuBar.getMenus().addAll(loginMenu);
		MenuItem loginItem = new MenuItem("Login");
		MenuItem registerItem = new MenuItem("Register");
		loginMenu.getItems().addAll(loginItem, registerItem);

		loginItem.setOnAction(e -> {
			new LoginPage(scene).show();
		});

		registerItem.setOnAction(e -> {
			new RegisterPage(scene).show();
		});

		return menuBar;
	}

	static MenuBar eventOrganizerNavbar(Scene scene, User currentUser) {
		MenuBar menuBar = new MenuBar();
		Menu EOMenu = new Menu("Actions");
		menuBar.getMenus().addAll(EOMenu);
		MenuItem createEvent, viewEvent;
		createEvent = new MenuItem("Create Event");
		viewEvent = new MenuItem("View Event");
		EOMenu.getItems().addAll(createEvent, viewEvent);

		createEvent.setOnAction(e -> {
			new CreateEventPage(scene, currentUser).show();
		});

		viewEvent.setOnAction(e -> {
			new ViewEventPage(scene, currentUser).show();
		});

		return menuBar;
	}
}
