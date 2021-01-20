package com.zack.projects.chatapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zack.projects.chatapp.exception.ResourceExistsException;
import com.zack.projects.chatapp.exception.ResourceNotFoundException;
import com.zack.projects.chatapp.model.Admin;
import com.zack.projects.chatapp.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public List<com.zack.projects.chatapp.model.Admin> getAdmins() {
		return this.adminRepository.findAll();
	}

	public Admin getAdminByUserName(String adminUserName) throws ResourceNotFoundException {

		return this.adminRepository.findById(adminUserName)
				.orElseThrow(() -> new ResourceNotFoundException("Admin user name [" + adminUserName + "] not Found."));
	}

	public Admin addAdmin(Admin admin) throws ResourceExistsException {

		boolean userNameExists = this.adminRepository.existsById(admin.getAdminUserName());

		if (userNameExists) {
			throw new ResourceExistsException("User name [" + admin.getAdminUserName() + "] is not available.");
		}

		return this.adminRepository.save(admin);

	}

	public Admin updateAdmin(Admin admin) throws ResourceNotFoundException {
		
		Admin update = this.adminRepository.findById(admin.getAdminUserName()).orElseThrow(
				() -> new ResourceNotFoundException("Admin user name [" + admin.getAdminUserName() + "] does not exist."));

		update.setAdminEmail(admin.getAdminEmail());

		return this.adminRepository.save(update);
		
	}

	public Admin deleteAdmin(String adminUserName) throws ResourceNotFoundException {
		
		Admin admin = this.adminRepository.findById(adminUserName)
				.orElseThrow(() -> new ResourceNotFoundException("Admin user name [" + adminUserName + "] not Found."));

		this.adminRepository.deleteById(adminUserName);

		return admin;

	}

}
