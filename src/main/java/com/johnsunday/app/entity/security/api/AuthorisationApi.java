package com.johnsunday.app.entity.security.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnsunday.app.entity.security.UserEmployee;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/auth")
public class AuthorisationApi {
	
	@Autowired AuthenticationManager authManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthorisationRequest request){
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));			
			UserEmployee user = (UserEmployee) authentication.getPrincipal();
			String accessToken = "JWT access token here";
			AuthorisationResponse response = new AuthorisationResponse(user.getUserEmail(),accessToken);
			return ResponseEntity.ok(response);
		}catch(BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
