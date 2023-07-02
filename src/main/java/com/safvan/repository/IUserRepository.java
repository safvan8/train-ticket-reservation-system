package com.safvan.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.safvan.beans.User;

public interface IUserRepository extends CrudRepository<User, Integer> {

	public User findByUsernameAndPassword(String usename, String password);

	@Transactional
	@Modifying
	@Query("UPDATE com.safvan.beans.User SET sessionId=:sessionId WHERE userId=:userId")
	public void updateSessionIdByUserId(Integer userId, String sessionId);

	public User findBySessionId(String sessionId);
}
