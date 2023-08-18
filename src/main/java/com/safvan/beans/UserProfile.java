package com.safvan.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * UserProfile class represents the profile information of a user.
 *
 * This class contains information such as first name, last name, address, phone
 * number, and profile image.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "user_profiles")
@Getter
@Setter
public class UserProfile {

	/**
	 * The unique identifier for the user profile.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_id")
	private Integer profileId;

	/**
	 * The first name of the user.
	 */
	@Column(name = "first_name")
	private String firstName;

	/**
	 * The last name of the user.
	 */
	@Column(name = "last_name")
	private String lastName;

	/**
	 * The address of the user.
	 */
	@Column(name = "address")
	private String address;

	/**
	 * The phone number of the user.
	 */
	@Column(name = "phone_number")
	private String phoneNumber;

	/**
	 * The profile image of the user .this will be displayed when user access his
	 * profile
	 */
	@Lob
	private byte[] image;

	@Override
	public String toString() {
		return "UserProfile [profileId=" + profileId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

}
