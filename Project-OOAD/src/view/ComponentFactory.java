package view;

import controller.AdminController;
import controller.EventOrganizerController;
import controller.GuestController;
import controller.UserController;
import controller.VendorController;
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
		else if (User.getCurrentUser().getRole().equals("Guest"))
			return guestNavbar();
		else
			return adminNavbar();
	}

	static MenuBar adminNavbar() {
		MenuBar menuBar = new MenuBar();
		Menu adminMenu = new Menu("Actions");
		menuBar.getMenus().add(adminMenu);

		MenuItem viewEvent, viewUser, logout;

		viewEvent = new MenuItem("View All Events");
		viewUser = new MenuItem("View All Users");
		logout = new MenuItem("Logout");

		adminMenu.getItems().addAll(viewEvent, viewUser, logout);

		viewEvent.setOnAction(e -> {
			AdminController.viewAllEvents();
		});

		viewUser.setOnAction(e -> {
			AdminController.viewAllUsers();
		});

		logout.setOnAction(e -> {
			UserController.logout();
		});

		return menuBar;
	}

	static MenuBar vendorNavbar() {
		MenuBar menuBar = new MenuBar();
		Menu vendorMenu = new Menu("Actions");
		menuBar.getMenus().addAll(vendorMenu);

		MenuItem viewInvitation, viewAcceptedInvitation, manageVendor, logout;
		viewInvitation = new MenuItem("View Invitation");
		viewAcceptedInvitation = new MenuItem("View Accepted Invitation");
		logout = new MenuItem("Logout");
		manageVendor = new MenuItem("Manage Vendor");

		vendorMenu.getItems().addAll(viewInvitation, viewAcceptedInvitation, manageVendor, logout);

		viewInvitation.setOnAction(e -> {
			UserController.viewInvitation();
		});

		viewAcceptedInvitation.setOnAction(e -> {
			VendorController.viewOrganizedEvents();
		});

		logout.setOnAction(e -> {
			UserController.logout();
		});

		manageVendor.setOnAction(e -> {
			VendorController.viewManageVendor();
		});

		return menuBar;
	}

	static MenuBar guestNavbar() {
		MenuBar menuBar = new MenuBar();
		Menu guestMenu = new Menu("Actions");
		menuBar.getMenus().add(guestMenu);

		MenuItem viewInvitation, viewAcceptedInvitation, logout;
		viewInvitation = new MenuItem("View Invitation");
		viewAcceptedInvitation = new MenuItem("View Accepted Invitation");
		logout = new MenuItem("Logout");

		guestMenu.getItems().addAll(viewInvitation, viewAcceptedInvitation, logout);

		viewInvitation.setOnAction(e -> {
			UserController.viewInvitation();
		});

		viewAcceptedInvitation.setOnAction(e -> {
			GuestController.viewOrganizedEvents();
		});

		logout.setOnAction(e -> {
			UserController.logout();
		});
		return menuBar;
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
