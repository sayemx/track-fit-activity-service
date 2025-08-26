package com.sayem.trackfit.activity.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {
	
	@GetMapping("/api/users/validateByuserId/{userId}")
	public ResponseEntity<Boolean> validateUserByUserId(@PathVariable String userId);
	
	@GetMapping("/api/users/validateByKeycloakId/{keycloakId}")
	public ResponseEntity<Boolean> validateUserByKeycloakId(@PathVariable String keycloakId);
	
}
