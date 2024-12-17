package view;

import java.util.List;
import java.util.Optional;

import controller.AdminController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;
import util.Result;

public class ViewUserPage extends Page<List<User>> {

	BorderPane mainPane;
	VBox mainBox;
	Label titleLabel;
	TableView<User> tableView;
	Button deleteButton;

	public void event() {
		deleteButton.setOnAction(e -> {
			User selectedUser = tableView.getSelectionModel().getSelectedItem();
			Result<Void, String> result = AdminController.userSelected(selectedUser);
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Are you sure you want to delete?");
			Optional<ButtonType> check = alert.showAndWait();

			if (check.isPresent()) {
				if (check.get() == ButtonType.OK)
					AdminController.deleteUser(selectedUser.getId());
			}
			AdminController.viewAllUsers();
		});
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainPane.setTop(ComponentFactory.adminNavbar());

		mainBox = new VBox();
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPadding(new Insets(20));

		titleLabel = new Label("View All User");
		titleLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));

		mainBox.getChildren().add(titleLabel);

		if (data.isEmpty() || data == null)
			mainBox.getChildren().add(new Label("You don't have any users yet"));
		else {
			tableView = AdminController.createTableView(data);
			mainBox.getChildren().add(tableView);
		}

		deleteButton = new Button("Delete User");

		mainBox.getChildren().add(deleteButton);

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
