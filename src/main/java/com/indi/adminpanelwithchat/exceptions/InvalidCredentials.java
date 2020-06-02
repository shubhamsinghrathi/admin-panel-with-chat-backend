package com.indi.adminpanelwithchat.exceptions;

public class InvalidCredentials extends RuntimeException {

	private static final long serialVersionUID = -2665330394143987596L;

	public InvalidCredentials(String message) {
		super(message);
	}
	
}
