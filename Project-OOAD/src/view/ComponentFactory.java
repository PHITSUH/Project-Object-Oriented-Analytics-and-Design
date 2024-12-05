package view;

import controller.EventOrganizerController;
import controller.UserController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ComponentFactory {
	static MenuBar landingNavbar() {
		MenuBar menuBar = new MenuBar();
		Menu loginMenu = new Menu("Actions");
		menuBar.getMenus().addAll(loginMenu);
		MenuItem loginItem = new MenuItem("Login");
		MenuItem registerItem = new MenuItem("Register");
		loginMenu.getItems().addAll(loginItem, registerItem);

		loginItem.setOnAction(e -> {
			UserController.viewLoginPage();
		});

		registerItem.setOnAction(e -> {
			UserController.viewRegisterPage();
		});

		return menuBar;
	}

	static MenuBar eventOrganizerNavbar() {
		MenuBar menuBar = new MenuBar();
		Menu EOMenu = new Menu("Actions");
		menuBar.getMenus().addAll(EOMenu);
		MenuItem createEvent, viewEvent;
		createEvent = new MenuItem("Create Event");
		viewEvent = new MenuItem("View Event");
		EOMenu.getItems().addAll(createEvent, viewEvent);

		createEvent.setOnAction(e -> {
			EventOrganizerController.viewCreateEventPage();
		});

		viewEvent.setOnAction(e -> {
			EventOrganizerController.viewViewEventPage();
		});

		return menuBar;
	}
}
