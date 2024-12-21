package view;

import java.util.List;

import controller.InvitationController;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Invitation;
import util.Result;

public class ViewInvitationPage extends Page<List<Invitation>> {

	private BorderPane mainPane;
	private VBox mainBox;
	private Label titleLabel;
	private TableView<Invitation> tableView;
	private HBox buttonBox;
	private Button acceptInvitationButton;

	public void event() {
		acceptInvitationButton.setOnAction(e -> {
			System.out.println("test");
			Invitation selectedInvitation = tableView.getSelectionModel().getSelectedItem();
			Result<Void, String> result = InvitationController.invitationSelected(selectedInvitation);
			if (result.isErr()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(result.getError());
				alert.show();
				return;
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText(Invitation.acceptInvitation(selectedInvitation.getInvitationId()));
			alert.show();
			UserController.viewInvitation();
		});
	}

	public Pane init() {
		mainPane = new BorderPane();
		mainBox = new VBox(20);
		mainBox.setAlignment(Pos.CENTER);

		mainPane.setTop(ComponentFactory.roleNavbar());

		titleLabel = new Label("View Invitation");
		titleLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 60));

		acceptInvitationButton = new Button("Accept Invitation");
		buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);

		buttonBox.getChildren().addAll(acceptInvitationButton);

		if (data == null || data.isEmpty()) {
			mainBox.getChildren().addAll(titleLabel, new Label("There are no invitation for you"));

		} else {
			tableView = InvitationController.createTableView(data);
			mainBox.getChildren().addAll(titleLabel, tableView, buttonBox);
		}

		mainBox.setPadding(new Insets(10));

		mainPane.setCenter(mainBox);
		event();

		return mainPane;
	}

	@Override
	Pane initRoot() {
		this.root = init();
		return root;
	}

}
