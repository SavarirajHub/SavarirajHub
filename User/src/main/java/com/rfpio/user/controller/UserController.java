package com.rfpio.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfpio.user.model.User;
import com.rfpio.user.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable("userId") int userId) {
		return new ResponseEntity<User>(userService.findById(userId), HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") int userId, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(userId, user), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAllUser() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("userId") int userId) {
		userService.deleteById(userId);
		return new ResponseEntity<String>("Record Deleted", HttpStatus.OK);
	}
	
	@GetMapping("/name/{userName}")
	public ResponseEntity<List<User>> findUserByName(@PathVariable("userName") String userName) {
		return new ResponseEntity<List<User>>(userService.findByUserName(userName), HttpStatus.OK);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<User> findUserByEmail(@PathVariable("email") String email) {
		return new ResponseEntity<User>(userService.findByEmail(email), HttpStatus.OK);
	}
	
}
