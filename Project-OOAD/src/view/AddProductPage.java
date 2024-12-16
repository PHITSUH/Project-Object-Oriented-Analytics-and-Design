package view;

import controller.VendorController;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;
import model.Vendor;
import util.Result;

public class AddProductPage extends Page<Void> {

	BorderPane mainPane;
	VBox mainBox;
	Label titleLabel, nameLabel, descLabel;
	TextField nameField;
	TextArea descArea;
	Button submitButton;
	HBox nameBox, descBox;

	public void event() {
		submitButton.setOnAction(e -> {
			Result<String, String> result = VendorController.checkManageVendorInput(nameField.getText(),
					descArea.getText());
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}
			Vendor.manageVendor(User.getCurrentUser(), nameField.getText(), descArea.getText());
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText(result.getValue());
			alert.showAndWait();
			closePopup();
			VendorController.viewManageVendor();
		});
	}

	public Pane init() {
		mainPane = new BorderPane();

		titleLabel = new Label("Add Product");
		titleLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 30));

		nameLabel = new Label("Name: ");

		nameField = new TextField();
		nameField.setPrefWidth(200);

		nameBox = new HBox(10);
		nameBox.getChildren().addAll(nameLabel, nameField);
		nameBox.setAlignment(Pos.CENTER);

		descLabel = new Label("Description: ");

		descArea = new TextArea();
		descArea.setPrefWidth(200);
		descArea.setPrefHeight(100);

		descBox = new HBox(10);
		descBox.getChildren().addAll(descLabel, descArea);
		descBox.setAlignment(Pos.CENTER);

		submitButton = new Button("Add Product");

		mainBox = new VBox(20);
		mainBox.getChildren().addAll(titleLabel, nameBox, descBox, submitButton);
		mainBox.setAlignment(Pos.CENTER);

		mainPane.setCenter(mainBox);
		event();

		return mainPane;
	}

	@Override
	Pane initRoot() {
		root = init();
		return root;
	}

}
