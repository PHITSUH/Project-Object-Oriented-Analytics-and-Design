package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginPage extends Page {

	public LoginPage(Scene scene) {
		super(scene);
	}

	BorderPane mainPane;
	TextField emailField, passwordField;
	Label emailLabel, passwordLabel, loginLabel, storeLabel;
	VBox mainBox, emailBox, passwordBox;
	Button submitButton;

	public void event() {
		submitButton.setOnMouseClicked(e -> {
			Alert alert = UserController.validateLogin(emailField.getText(), passwordField.getText());
			alert.showAndWait().ifPresent(response -> {
				if (alert.getAlertType().equals(AlertType.CONFIRMATION)) {
					UserController.loginByRole(emailField.getText(), scene);
				}
			});
		});
	}

	public Pane init() {
		mainPane = new BorderPane();
		MenuBar navigationBar = ComponentFactory.landingNavbar(scene);
		mainPane.setTop(navigationBar);

		loginLabel = new Label("Login");
		storeLabel = new Label("StellarFest");
		storeLabel.setFont(Font.font(20));
		loginLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));

		emailLabel = new Label("Email");
		passwordLabel = new Label("Password");

		emailField = new TextField();
		emailField.setPromptText("Email");
		emailField.setMaxWidth(200);
		passwordField = new PasswordField();
		passwordField.setPromptText("Password");
		passwordField.setMaxWidth(200);

		emailBox = new VBox();
		emailBox.getChildren().addAll(emailLabel, emailField);
		emailBox.setAlignment(Pos.CENTER);
		emailBox.setMargin(emailLabel, new Insets(30, 171, 0, 0));
		passwordBox = new VBox();
		passwordBox.getChildren().addAll(passwordLabel, passwordField);
		passwordBox.setAlignment(Pos.CENTER);
		passwordBox.setMargin(passwordLabel, new Insets(10, 150, 0, 0));
		passwordBox.setMargin(passwordField, new Insets(0, 0, 30, 0));

		submitButton = new Button("Login");

		mainBox = new VBox();
		mainBox.setAlignment(Pos.TOP_CENTER);
		mainBox.getChildren().addAll(loginLabel, storeLabel, emailBox, passwordBox, submitButton);

		mainPane.setCenter(mainBox);

		event();

		return mainPane;
	}

	@Override
	Pane initRoot() {
		Pane root = init();
		return root;
	}

}
