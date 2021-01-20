package com.zack.projects.chatapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.zack.projects.chatapp.auth.ApplicationUser;
import com.zack.projects.chatapp.dao.ApplicationUserDao;
import com.zack.projects.chatapp.repository.UserRepository;
import static com.zack.projects.chatapp.security.UserRole.*;

@Repository("fake")
public class ApplicationUserDaoService implements ApplicationUserDao {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	public ApplicationUserDaoService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectChatappApplicationUserByUserName(String username) {
		return getApplicationUsers().stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		
		List<ApplicationUser> applicationUsers = Lists.newArrayList();
		
		userRepository.findAll()
					.stream()
					.forEach(user 
							-> 	applicationUsers.add(new ApplicationUser(
									USER.getGrantedAuthorities(),
									passwordEncoder.encode(user.getPassword()),
									user.getUserName(),
									true, true, true, true)));
		
		return applicationUsers;
							
	}

}
