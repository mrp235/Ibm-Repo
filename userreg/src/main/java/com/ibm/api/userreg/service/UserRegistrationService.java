package com.ibm.api.userreg.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.ibm.api.userreg.dto.GeoLocationAPIResponse;
import com.ibm.api.userreg.dto.UserRegistrationRequest;
import com.ibm.api.userreg.dto.UserRegistrationResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Validated
@Slf4j
public class UserRegistrationService {

	private static final String CANADA = "Canada";
	private static final String SERVER_URI = "http://ip-api.com/json/";
	private static final String RESP_FIELDS = "?fields=status,message,country,countryCode,region,regionName,city,zip,timezone,isp";
	
	@Autowired
	private RestTemplate restTemplate;
	

	public UserRegistrationResponse registerUser(@Valid UserRegistrationRequest req) throws Exception {
		
		//Checking for the location of the user
		log.info("Checking the location of the user {} for the IP :{}", req.getUsername(), req.getIpAddress());
		
		GeoLocationAPIResponse apiresponse = restTemplate.getForObject(SERVER_URI + req.getIpAddress() + RESP_FIELDS, GeoLocationAPIResponse.class);
		log.info("User {}'s IP -{} belongs to the country {}", req.getUsername(), req.getIpAddress(), apiresponse.getCountry());

		if(!CANADA.equals(apiresponse.getCountry())){
			throw new IllegalArgumentException("User is not eligible to register");
		}
		
		UserRegistrationResponse usrResp = new UserRegistrationResponse();
		usrResp.setUuid(UUID.randomUUID());
		usrResp.setMessage("Welcome "+ req.getUsername() + " from " + apiresponse.getCity() + " of " + apiresponse.getCountry());
		log.info("ALL validations passed, hence returning success response for the user");
		
		return usrResp;
	}
	
}
