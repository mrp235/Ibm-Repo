package com.ibm.api.userreg.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.ibm.api.userreg.dto.GeoLocationAPIResponse;
import com.ibm.api.userreg.dto.UserRegistrationRequest;
import com.ibm.api.userreg.dto.UserRegistrationResponse;

@ExtendWith(SpringExtension.class)
public class UserRegistrationServiceTest {
  
  @InjectMocks
  private UserRegistrationService service;

  @Mock
  private RestTemplate restTemplate;
  
  @Test
  public void whenUserCountryIsNotCanada_thenThrowsException() throws Exception {
    UserRegistrationRequest req = validUserRegistrationRequestNotCanda();
    
    GeoLocationAPIResponse apiresponse = new GeoLocationAPIResponse(); 
    apiresponse.setCountry("United States");
    
    when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(apiresponse);
    
    try {
    	service.registerUser(req);
    }catch(IllegalArgumentException e) {}
       
  }

  @Test
  public void whenUserRegistrationRequestIsValid_thenReturnsSuccess() throws Exception {
    
	  UserRegistrationRequest req = validUserRegistrationRequestCanada();
	    
	  GeoLocationAPIResponse apiresponse = new GeoLocationAPIResponse(); 
	  apiresponse.setCountry("Canada");
	  apiresponse.setCity("Toronto");
	  
	  when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(apiresponse);
	    
	  UserRegistrationResponse usrResp = service.registerUser(req);
	  assertTrue(usrResp.getMessage().contains("Welcome"));  	
		
  }


  private UserRegistrationRequest invalidUserRegistrationRequest() {
    UserRegistrationRequest UserRegistrationRequest = new UserRegistrationRequest();
    UserRegistrationRequest.setIpAddress("100.10.10.10");
    UserRegistrationRequest.setUsername("");
    UserRegistrationRequest.setPassword("A121_131$");
    return UserRegistrationRequest;
  }
  
  private UserRegistrationRequest validUserRegistrationRequestCanada() {
    UserRegistrationRequest UserRegistrationRequest = new UserRegistrationRequest();
    UserRegistrationRequest.setIpAddress("192.206.151.131");
    UserRegistrationRequest.setUsername("Mukti");
    UserRegistrationRequest.setPassword("A121_131$");
    return UserRegistrationRequest;
  }
  
  private UserRegistrationRequest validUserRegistrationRequestNotCanda() {
	  UserRegistrationRequest UserRegistrationRequest = new UserRegistrationRequest();
	  UserRegistrationRequest.setIpAddress("19.206.151.131");
	  UserRegistrationRequest.setUsername("Mukti");
	  UserRegistrationRequest.setPassword("A121_131$");
	  return UserRegistrationRequest;
  }

}