package controller;

import java.util.List;

import model.Invitation;

public class InvitationController {

	public InvitationController() {

	}

	public static List<Invitation> getInvitationsByEmail(String email) {
		// TODO Auto-generated method stub
		return Invitation.getInvitationsByEmail(email);
	}

	public static String acceptInvitation(String InvitationId) {
		return Invitation.acceptInvitation(InvitationId);
	}

}