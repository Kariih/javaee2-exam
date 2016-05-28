package utils;

import model.User;

public enum AuthStatus {
	AUTHENTICATED,
	GUEST;
	
	public static class State{
		public static AuthStatus status = AuthStatus.GUEST;
		public static User user;
	}	
}

