package com.zack.projects.chatapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "admins")
public class Admin {
	
	@Id
	@Column(name = "adminUserName", nullable = false)
	@NotBlank(message = "adminUserName is mandatory")
	private String adminUserName;
	
	@Column(name = "adminEmail")
	private String adminEmail;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	public Admin() {}

	public Admin(String adminUserName, String adminEmail, String password) {
		super();
		this.adminUserName = adminUserName;
		this.adminEmail = adminEmail;
		this.password = password;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String userName) {
		this.adminUserName = userName;
	}
	
	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminUserName == null) ? 0 : adminUserName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (adminUserName == null) {
			if (other.adminUserName != null)
				return false;
		} else if (!adminUserName.equals(other.adminUserName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin [adminUserName=" + adminUserName + ", adminEmail=" + adminEmail + "]";
	}

}
