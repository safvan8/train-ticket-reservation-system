package com.safvan.util;

import com.safvan.beans.User;
import com.safvan.beans.UserProfile;

/**
 * This class provides utility methods for creating User objects with associated
 * UserProfile. It encapsulates the logic of constructing a User object with the
 * given attributes.
 * 
 * @author Safvan
 * @version 1.0
 * @since 1.0
 * 
 */
public class UserUtils {

	/**
	 * Creates a new `User` object with the specified attributes.
	 *
	 * @param username    The username of the user.
	 * @param password    The password of the user.
	 * @param firstName   The first name of the user.
	 * @param lastName    The last name of the user.
	 * @param address     The address of the user.
	 * @param phoneNumber The phone number of the user.
	 * @param imageBytes  The byte array representing the user's profile image.
	 * @return The created User object.
	 */
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
