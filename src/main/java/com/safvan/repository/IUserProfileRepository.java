package com.safvan.repository;

import org.springframework.data.repository.CrudRepository;

import com.safvan.beans.UserProfile;

public interface IUserProfileRepository extends CrudRepository<UserProfile, Integer> {

}
