package com.ibm.api.userreg.dto;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest implements Serializable{

	private static final long serialVersionUID = -3747898414345347154L;

	
	String username;
	
	@NotNull(message = "Password can not be null")
	@NotEmpty(message = "Password can not be empty")
	@NotBlank(message = "Password can not be blank")
	@Pattern(regexp = "^((?=.*\\d)(?=.*[A-Z])(?=.*[_#\\$%.]).{8,})$")
	String password;
	
	@NotNull(message = "IPAddress can not be null")
	@NotEmpty(message = "IPAddress can not be empty")
	@NotBlank(message = "IPAddress can not be blank")
	String ipAddress;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
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
		UserRegistrationRequest other = (UserRegistrationRequest) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (ipAddress == null) {
			if (other.ipAddress != null) {
				return false;
			}
		} else if (!ipAddress.equals(other.ipAddress)) {
			return false;
		}
				
		return true;
	}
	
	
	
}
