package com.ibm.api.userreg.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationResponse {
	
	UUID uuid;
	String message;

}
