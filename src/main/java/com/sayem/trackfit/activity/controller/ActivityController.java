package com.sayem.trackfit.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayem.trackfit.activity.dto.ActivityRequest;
import com.sayem.trackfit.activity.dto.ActivityResponse;
import com.sayem.trackfit.activity.service.IActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
	
	@Autowired
	private IActivityService activityService;
	
	@PostMapping
	public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest,
															@RequestHeader("X-User-ID") String userId) {
		if(userId != null) {
			activityRequest.setUserId(userId);
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(activityService.trackActivity(activityRequest));
	}
	
	@GetMapping
	public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") String userId) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(activityService.getUserActivities(userId));
	}
	
	@GetMapping("/{activityId}")
	public ResponseEntity<ActivityResponse> getActivityById(@PathVariable String activityId) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(activityService.getActivityById(activityId));
	}
	
	
}
