package com.zack.projects.chatapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "userName", nullable = false)
	@NotBlank(message = "UserName is mandatory")
	private String userName;
	
	@Column(name = "userEmail")
	private String userEmail;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "isAccountNonExpired")
	private boolean isAccountNonExpired;
	
	@Column(name = "isAccountNonLocked")
	private boolean isAccountNonLocked;
	
	@Column(name = "isCredentialsNonExpired")
	private boolean isCredentialsNonExpired;
	
	@Column(name = "isEnabled")
	private boolean isEnabled;
	
	@Column(name = "isOnline")
	private boolean isOnline;
	
	public User() {
		super();
	}

	public User(String userName, 
			String userEmail, 
			String password, 
			boolean isAccountNonExpired, 
			boolean isAccountNonLocked, 
			boolean isCredentialsNonExpired, 
			boolean isEnabled,
			boolean isOnline) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.isOnline = isOnline;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void activateAcount() {
		setAccountNonExpired(true);
		setAccountNonLocked(true);
		setCredentialsNonExpired(true);
		setEnabled(true);
		setOnline(false);
	}
	
	public boolean isAccountActive() {
		return isAccountNonExpired() && isAccountNonLocked() && isCredentialsNonExpired() && isEnabled();
	}
	

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName 
				+ ", userEmail=" + userEmail + "]";
	}

	
	
}
