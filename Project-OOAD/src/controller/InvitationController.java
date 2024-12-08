package controller;

import java.util.ArrayList;

import model.Invitation;

public class InvitationController {

	public InvitationController() {
		
	}
	
	private ArrayList<Invitation> getInvitation(String email) {
		// TODO Auto-generated method stub
		return Invitation.getInvitations(email);
	}

}