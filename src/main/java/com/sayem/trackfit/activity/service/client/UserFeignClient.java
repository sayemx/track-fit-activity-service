package com.sayem.trackfit.activity.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {
	
	@GetMapping("/api/users/validate/{userId}")
	public ResponseEntity<Boolean> validateUser(@PathVariable String userId);
	
}
