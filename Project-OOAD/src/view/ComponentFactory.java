package view;

import controller.EventOrganizerController;
import controller.UserController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.User;

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

	static MenuItem changeProfileMenuItem() {
		MenuItem changeProfile = new MenuItem("Change Profile");
		return changeProfile;
	}

	static MenuBar roleNavbar() {
		if (User.getCurrentUser().getRole().equals("Event Organizer"))
			return eventOrganizerNavbar();
		else if (User.getCurrentUser().getRole().equals("Vendor"))
			return vendorNavbar();
		return null;
	}

	static MenuBar vendorNavbar() {

		return null;
	}

	static MenuBar eventOrganizerNavbar() {
		MenuBar menuBar = new MenuBar();
		Menu EOMenu = new Menu("Actions");
		menuBar.getMenus().addAll(EOMenu);
		MenuItem createEvent, viewEvent, logout;
		createEvent = new MenuItem("Create Event");
		viewEvent = new MenuItem("View Event");
		logout = new MenuItem("Logout");
		EOMenu.getItems().addAll(createEvent, viewEvent, logout);

		createEvent.setOnAction(e -> {
			EventOrganizerController.viewCreateEventPage();
		});

		viewEvent.setOnAction(e -> {
			EventOrganizerController.viewOrganizedEvents();
		});

		logout.setOnAction(e -> {
			UserController.logout();
		});

		return menuBar;
	}
}
