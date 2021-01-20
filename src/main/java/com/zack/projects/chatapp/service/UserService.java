package com.zack.projects.chatapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zack.projects.chatapp.exception.ResourceExistsException;
import com.zack.projects.chatapp.exception.ResourceNotFoundException;
import com.zack.projects.chatapp.model.Profile;
import com.zack.projects.chatapp.model.User;
import com.zack.projects.chatapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	// Get Users
	public List<Profile> getUsers() {
		
		List<User> userList = this.userRepository.findAll();
		
		List<Profile> profileList = new ArrayList<>();
		
		userList
			.stream()
			.forEach(user -> {
				Profile profile = new Profile(user.getUserName(), user.getUserEmail());
				profileList.add(profile);
			});
		
		return profileList;

	}

	// Get User by id
	public Profile getUserByUserName(String userName)
			throws ResourceNotFoundException {

		User user = this.userRepository
				.findById(userName)
				.orElseThrow(() -> 
				new ResourceNotFoundException(
						"User name [" + userName + "] not Found."));

		Profile profile = new Profile(user.getUserName(), user.getUserEmail());
		
		return profile;
	}

	// Add User
	public User addUser(User user) throws ResourceExistsException {

		boolean userNameExists = this.userRepository
				.existsById(user.getUserName());

		if (userNameExists) {
			throw new ResourceExistsException(
					"User name [" + user.getUserName() + "] is not available.");
		}
		
		return this.userRepository.save(user);

	}

	// Update User
	public User updateUser(User user) throws ResourceNotFoundException {

		User update = this.userRepository
				.findById(user.getUserName())
				.orElseThrow(() -> 
				new ResourceNotFoundException(
						"User name [" + user.getUserName() + "] does not exist."));

		update.setUserEmail(user.getUserEmail());

		return this.userRepository.save(update);

	}

	// Delete User
	public User deleteUser(String userName)
			throws ResourceNotFoundException {

		User user = this.userRepository
				.findById(userName)
				.orElseThrow(() -> 
				new ResourceNotFoundException(
						"User name [" + userName + "] not Found."));

		this.userRepository.deleteById(userName);

		return user;

	}

}
