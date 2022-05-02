package com.rfpio.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rfpio.user.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
	
	@Query(value = "{'userName': ?0}")
	public List<User> findByUserName(String userName);
	
	@Query(value = "{'email': ?0}")
	public User findByEmail(String email);

}
