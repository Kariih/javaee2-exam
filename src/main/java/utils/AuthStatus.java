package utils;

import model.User;

public final class AuthStatus {

	public static enum Status {
		AUTHENTICATED, GUEST
	}

	private static User user;
	private static Status status = Status.GUEST;

	private AuthStatus() {
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		AuthStatus.user = user;
	}

	public static Status getStatus() {
		return status;
	}

	public static void setStatus(Status status) {
		AuthStatus.status = status;
	}
	
}
