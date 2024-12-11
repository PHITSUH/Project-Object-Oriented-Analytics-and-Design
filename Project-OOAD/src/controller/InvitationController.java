package controller;

import java.util.ArrayList;

import model.Invitation;

public class InvitationController {

	public InvitationController() {
		

	}
	

	public static ArrayList<Invitation> getInvitation(String email) {
		// TODO Auto-generated method stub
		return Invitation.getInvitations(email);
		return Invitation.getInvitationsByEmail(email);
	}
	

	public static String acceptInvitation(String InvitationId) {
		return Invitation.acceptInvitation(InvitationId);
	}

}