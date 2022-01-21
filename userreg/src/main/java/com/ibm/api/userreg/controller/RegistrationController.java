package com.ibm.api.userreg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.api.userreg.dto.UserRegistrationRequest;
import com.ibm.api.userreg.dto.UserRegistrationResponse;
import com.ibm.api.userreg.service.UserRegistrationService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller for User Registration.
 *
 * @author 
 */
@RestController

@RequestMapping("/user-registration")
@OpenAPIDefinition(info = @Info(title = "User Registration API", version = "1.0", description = "User Registration Information"))
@Slf4j
public class RegistrationController {
	
	@Autowired
	private UserRegistrationService regService;
		
	
	//@ApiOperation(httpMethod = "POST", value = "Register user by username and password and validate against location")
	@PostMapping(value="/register", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserRegistrationRequest req) throws Exception {
		
		log.info("Request received for the registration for the user {}", req.getUsername());
		UserRegistrationResponse regResp = regService.registerUser(req);
		return ResponseEntity.ok(regResp);
	}

}
