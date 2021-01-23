package com.zack.projects.chatapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zack.projects.chatapp.exception.ResourceExistsException;
import com.zack.projects.chatapp.exception.ResourceNotFoundException;
import com.zack.projects.chatapp.model.Profile;
import com.zack.projects.chatapp.model.User;
import com.zack.projects.chatapp.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	// Get Users
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<Profile> getUsers() {
		return this.userService.getUsers();
	}
	
	@GetMapping("/online")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<Profile> getOnlineUsers() {
		return this.userService.getOnlineUsers();
	}
	
	@GetMapping("/offline")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<Profile> getOfflineUsers() {
		return this.userService.getOfflineUsers();
	}
	
	// Get User by id
	@GetMapping("{userName}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public Profile getUserByUserName(
			@PathVariable(value = "userName") String userName) 
					throws ResourceNotFoundException {
		
		return this.userService.getUserByUserName(userName);
		
	}
	
	public User addUser(User user) throws ResourceExistsException {
		
		return this.userService.addUser(user);
		
	}
	
	
	// Update User
	@PutMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public User updateUser(@RequestBody User user) 
			throws ResourceNotFoundException {
		
		return this.userService.updateUser(user);
		
	}
	
	// Delete User
	@DeleteMapping("{userName}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public User deleteUser(
			@PathVariable(value ="userName") String userName) 
					throws ResourceNotFoundException {
		
		return this.userService.deleteUser(userName);
		
	}

}
