package com.safvan.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.safvan.constants.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer userId;

	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Column(name = "session_id")
	private String sessionId;

	// Add this property for profile reference
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private UserProfile userProfile;

	/**
	 * @OneToOne indicates a one-to-one relationship between User and UserProfile.
	 *           It means that each User entity can be associated with only one
	 *           UserProfile entity, and vice versa.
	 * 
	 *           fetch = FetchType.LAZY specifies that the associated UserProfile
	 *           entity should be loaded lazily. It means that the UserProfile
	 *           entity will be fetched from the database only when it is explicitly
	 *           accessed.
	 * 
	 *           cascade = CascadeType.ALL specifies that any changes made to the
	 *           User entity should be cascaded to the associated UserProfile
	 *           entity. This means that if you save, update, or delete a User
	 *           entity, the corresponding UserProfile entity will also be saved,
	 *           updated, or deleted.
	 * 
	 * @JoinColumn(name = "profile_id") indicates that the association is mapped by
	 *                  a foreign key column named "profile_id" in the User table.
	 *                  This column will hold the foreign key value referencing the
	 *                  primary key of the associated UserProfile entity.
	 */
}
