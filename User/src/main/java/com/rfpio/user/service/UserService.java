package com.rfpio.user.service;

import java.util.List;

import com.rfpio.user.model.User;

public interface UserService {
	public User save(User user);
	public User updateUser(int userId, User user);
	public List<User> findAll();
	public User findById(int userId);
	public void deleteById(int userId);
	public List<User> findByUserName(String userName);
	public User findByEmail(String email);
}
