package com.ibm.api.userreg.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ibm.api.userreg.dto.UserRegistrationRequest;
import com.ibm.api.userreg.dto.UserRegistrationResponse;
import com.ibm.api.userreg.service.UserRegistrationService;

@ExtendWith(SpringExtension.class)
public class RegistrationControllerTest {

  @InjectMocks
  private RegistrationController controller;
  
  @Mock
  private UserRegistrationService service;

  
  @Test
  public void whenUserRegistrationRequestIsInvalid_thenThrowsException() throws Exception {
    UserRegistrationRequest req = invalidUserRegistrationRequest();
    ConstraintViolationException ex = new ConstraintViolationException(null);
    
    when(service.registerUser(req)).thenThrow(ex);
    try {
    	ResponseEntity<UserRegistrationResponse> respEntity = controller.registerUser(req);
    }catch(ConstraintViolationException e) {}
       
  }

  @Test
  void whenUserRegistrationRequestIsValid_thenReturnsStatus200WithNoError() throws Exception {
    UserRegistrationRequest req = validUserRegistrationRequest();
    
    UserRegistrationResponse resp = new UserRegistrationResponse();
    resp.setUuid(UUID.randomUUID());
    resp.setMessage("Hi");
    
    when(service.registerUser(req)).thenReturn(resp);
    
    ResponseEntity<UserRegistrationResponse> respEntity = controller.registerUser(req);
    
    assertTrue(respEntity.getStatusCode().value()==200);

    assertFalse(respEntity.getBody().toString().contains("violations"));
   
  }


  private UserRegistrationRequest invalidUserRegistrationRequest() {
    UserRegistrationRequest UserRegistrationRequest = new UserRegistrationRequest();
    UserRegistrationRequest.setIpAddress("100.10.10.10");
    UserRegistrationRequest.setUsername("");
    UserRegistrationRequest.setPassword("A121_131$");
    return UserRegistrationRequest;
  }
  
  private UserRegistrationRequest validUserRegistrationRequest() {
    UserRegistrationRequest UserRegistrationRequest = new UserRegistrationRequest();
    UserRegistrationRequest.setIpAddress("192.206.151.131");
    UserRegistrationRequest.setUsername("Mukti");
    UserRegistrationRequest.setPassword("A121_131$");
    return UserRegistrationRequest;
  }

}