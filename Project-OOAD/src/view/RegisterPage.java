package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import model.User;
import util.Result;

public class RegisterPage extends Page<Void> {

	private BorderPane mainPane;
	private Label registerLabel, storeLabel, emailLabel, usernameLabel, passwordLabel, roleLabel;
	private VBox mainBox, emailBox, usernameBox, passwordBox, roleBox;
	private TextField usernameField, emailField;
	private PasswordField passwordField;
	private ComboBox<String> roleComboBox;
	private Button submitButton;

	public void event() {
		submitButton.setOnAction(e -> {
			Result<Void, String> result = UserController.register(emailField.getText(), usernameField.getText(),
					passwordField.getText(), roleComboBox.getValue());

			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR, result.getError());
				alert.show();
				return;
			}

			Alert alert = new Alert(AlertType.INFORMATION, "Account successfully created");
			alert.showAndWait();
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

		mainPane.setTop(ComponentFactory.landingNavbar());

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
		roleComboBox.getItems().addAll(User.GUEST_ROLE, User.EVENT_ORGANIZER_ROLE, User.VENDOR_ROLE);
		roleComboBox.setPrefWidth(200);
		// kasih default value guest agar inputnya tidak bisa kosong
		roleComboBox.getSelectionModel().select(0);

		emailBox = new VBox();
		emailBox.getChildren().addAll(emailLabel, emailField);
		emailBox.setAlignment(Pos.CENTER);
		VBox.setMargin(emailLabel, new Insets(10, 171, 0, 0));

		usernameBox = new VBox();
		usernameBox.getChildren().addAll(usernameLabel, usernameField);
		usernameBox.setAlignment(Pos.CENTER);
		VBox.setMargin(usernameLabel, new Insets(10, 148, 0, 0));

		passwordBox = new VBox();
		passwordBox.getChildren().addAll(passwordLabel, passwordField);
		passwordBox.setAlignment(Pos.CENTER);
		VBox.setMargin(passwordLabel, new Insets(10, 150, 0, 0));

		roleBox = new VBox();
		roleBox.getChildren().addAll(roleLabel, roleComboBox);
		roleBox.setAlignment(Pos.CENTER);
		VBox.setMargin(roleLabel, new Insets(10, 175, 0, 0));
		VBox.setMargin(roleComboBox, new Insets(0, 0, 50, 0));

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
