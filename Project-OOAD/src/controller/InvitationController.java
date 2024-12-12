package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Invitation;
import util.Result;

public class InvitationController extends Controller {
	public static TableView<Invitation> createTableView(List<Invitation> invitationList) {
		TableView<Invitation> tableView = new TableView<>();

		if (invitationList.isEmpty()) {
			return null;
		} else {
			tableView = new TableView<>();

			TableColumn<Invitation, Integer> invitationIdColumn = new TableColumn<>("Invitation Id");
			invitationIdColumn.setCellValueFactory(new PropertyValueFactory<>("invitationId"));
			invitationIdColumn.prefWidthProperty().bind(tableView.widthProperty().divide(4));

			TableColumn<Invitation, String> eventIdColumn = new TableColumn<>("Event Id");
			eventIdColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
			eventIdColumn.prefWidthProperty().bind(tableView.widthProperty().divide(4));

			TableColumn<Invitation, String> userIdColumn = new TableColumn<>("User Id");
			userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
			userIdColumn.prefWidthProperty().bind(tableView.widthProperty().divide(4));

			TableColumn<Invitation, String> invitationStatusColumn = new TableColumn<>("Invitation Status");
			invitationStatusColumn.setCellValueFactory(new PropertyValueFactory<>("invitationStatus"));
			invitationStatusColumn.prefWidthProperty().bind(tableView.widthProperty().divide(4));

			tableView.getColumns().addAll(invitationIdColumn, eventIdColumn, userIdColumn, invitationStatusColumn);

			ObservableList<Invitation> guestObsList = FXCollections.observableArrayList(invitationList);

			tableView.setItems(guestObsList);
		}

		return tableView;
	}

	public static List<Invitation> getInvitationsByEmail(String email) {
		// TODO Auto-generated method stub
		return Invitation.getInvitationsByEmail(email);
	}

	public static String acceptInvitation(String InvitationId) {
		return Invitation.acceptInvitation(InvitationId);
	}
	
	

	public static Result<Void, String> invitationSelected(Invitation invitation) {
		if (invitation == null)
			return Result.err("You must pick an invitation first");
		return Result.ok(null);
	}

}