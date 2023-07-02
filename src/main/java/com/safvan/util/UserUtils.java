package com.safvan.util;

import com.safvan.beans.User;
import com.safvan.beans.UserProfile;

public class UserUtils {

	public static User createUser(String username, String password, String firstName, String lastName, String address,
			String phoneNumber, byte[] imageBytes) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(firstName);
		userProfile.setLastName(lastName);
		userProfile.setAddress(address);
		userProfile.setPhoneNumber(phoneNumber);

		if (imageBytes != null) {
			userProfile.setImage(imageBytes);
		}

		user.setUserProfile(userProfile);

		return user;
	}

}
