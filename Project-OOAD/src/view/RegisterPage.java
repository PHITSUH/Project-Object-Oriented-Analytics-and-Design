package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegisterPage extends Page {
	public RegisterPage(Scene scene) {
		super(scene);
	}

	Alert alert;
	BorderPane mainPane;
	Label registerLabel, storeLabel, emailLabel, usernameLabel, passwordLabel, roleLabel;
	VBox mainBox, emailBox, usernameBox, passwordBox, roleBox;
	TextField usernameField, emailField;
	PasswordField passwordField;
	ComboBox<String> roleComboBox;
	Button submitButton;

	public void event() {
		submitButton.setOnMouseClicked(e -> {
			alert = UserController.validateRegister(emailField.getText(), usernameField.getText(),
					passwordField.getText(), roleComboBox.getValue());
			alert.showAndWait().ifPresent(response -> {
				if (alert.getAlertType().equals(AlertType.CONFIRMATION)) {
					new LoginPage(scene).show();
				}
			});
		});

	}

	public Pane init() {
		mainPane = new BorderPane();
		mainBox = new VBox();
		registerLabel = new Label("Register");
		storeLabel = new Label("StellarFest");
		storeLabel.setFont(Font.font(20));
		registerLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));
		mainPane.setCenter(mainBox);

		mainPane.setTop(ComponentFactory.landingNavbar(scene));

		usernameLabel = new Label("Username");
		emailLabel = new Label("Email");
		passwordLabel = new Label("Password");
		roleLabel = new Label("Role");

		usernameField = new TextField();
		usernameField.setPromptText("Username");
		usernameField.setMaxWidth(200);
		emailField = new TextField();
		emailField.setPromptText("Email");
		emailField.setMaxWidth(200);
		passwordField = new PasswordField();
		passwordField.setPromptText("Password");
		passwordField.setMaxWidth(200);
		roleComboBox = new ComboBox<>();
		roleComboBox.getItems().addAll("Event Organizer", "Vendor", "Guest");
		roleComboBox.setPrefWidth(200);

		emailBox = new VBox();
		emailBox.getChildren().addAll(emailLabel, emailField);
		emailBox.setAlignment(Pos.CENTER);
		emailBox.setMargin(emailLabel, new Insets(10, 171, 0, 0));
		usernameBox = new VBox();
		usernameBox.getChildren().addAll(usernameLabel, usernameField);
		usernameBox.setAlignment(Pos.CENTER);
		usernameBox.setMargin(usernameLabel, new Insets(10, 148, 0, 0));
		passwordBox = new VBox();
		passwordBox.getChildren().addAll(passwordLabel, passwordField);
		passwordBox.setAlignment(Pos.CENTER);
		passwordBox.setMargin(passwordLabel, new Insets(10, 150, 0, 0));
		roleBox = new VBox();
		roleBox.getChildren().addAll(roleLabel, roleComboBox);
		roleBox.setAlignment(Pos.CENTER);
		roleBox.setMargin(roleLabel, new Insets(10, 175, 0, 0));
		roleBox.setMargin(roleComboBox, new Insets(0, 0, 50, 0));

		submitButton = new Button("Register");

		mainBox.setAlignment(Pos.TOP_CENTER);
		mainBox.getChildren().addAll(registerLabel, storeLabel, emailBox, usernameBox, passwordBox, roleBox,
				submitButton);

		event();

		return mainPane;
	}

	@Override
	Pane initRoot() {
		Pane root = init();
		return root;
	}
}
