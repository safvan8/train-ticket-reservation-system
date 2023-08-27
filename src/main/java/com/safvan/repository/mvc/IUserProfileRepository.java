package com.safvan.repository.mvc;

import org.springframework.data.repository.CrudRepository;

import com.safvan.beans.UserProfile;

/**
 * IUserProfileRepository interface.
 * 
 * This interface extends the CrudRepository interface provided by Spring Data.
 * It allows performing CRUD operations on the UserProfile entity.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
public interface IUserProfileRepository extends CrudRepository<UserProfile, Integer> {
	// Add custom methods if needed
}
