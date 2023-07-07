package com.safvan.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.safvan.beans.User;

/**
 * IUserRepository interface.
 * 
 * This interface extends the CrudRepository interface provided by Spring Data.
 * It allows performing CRUD operations on the User entity.
 *
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
public interface IUserRepository extends CrudRepository<User, Integer> {
	
	/**
     * Find a user by username and password.
     * this method id used for User authenatication while logging in.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return the user with the specified username and password
     */
	public User findByUsernameAndPassword(String usename, String password);
	
	/**
     * Update the session ID for a user.
     * 
     * @param userId    the ID of the user
     * @param sessionId the new session ID
     */
	@Transactional
	@Modifying
	@Query("UPDATE com.safvan.beans.User SET sessionId=:sessionId WHERE userId=:userId")
	public void updateSessionIdByUserId(Integer userId, String sessionId);

	/**
     * Find a user by session ID.
     * 
     * @param sessionId the session ID of the user
     * @return the user with the specified session ID
     */
	public User findBySessionId(String sessionId);
	
}
