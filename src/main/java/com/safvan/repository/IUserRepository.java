package com.safvan.repository;

import org.springframework.data.repository.CrudRepository;

import com.safvan.beans.User;

public interface IUserRepository extends CrudRepository<User, Integer> {

	public User findByUsernameAndPassword(String usename, String password);
}
