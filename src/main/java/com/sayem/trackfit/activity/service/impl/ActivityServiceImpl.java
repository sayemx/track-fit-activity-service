package com.sayem.trackfit.activity.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sayem.trackfit.activity.dto.ActivityRequest;
import com.sayem.trackfit.activity.dto.ActivityResponse;
import com.sayem.trackfit.activity.entity.Activity;
import com.sayem.trackfit.activity.exception.ActivityNotFoundException;
import com.sayem.trackfit.activity.exception.UserNotFoundException;
import com.sayem.trackfit.activity.repository.ActivityRepository;
import com.sayem.trackfit.activity.service.IActivityService;
import com.sayem.trackfit.activity.service.client.UserFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {
	
	private final ActivityRepository activityRepositoty;
	private final UserFeignClient userfeigClient;

	@Override
	public ActivityResponse trackActivity(ActivityRequest activityRequest) {
		
		ResponseEntity<Boolean> validateUser = userfeigClient.validateUser(activityRequest.getUserId());
		
		if(!validateUser.getBody()) {
			throw new UserNotFoundException(activityRequest.getUserId());
		}
		
		Activity activity = Activity.builder()
				.userId(activityRequest.getUserId())
				.type(activityRequest.getType())
				.duration(activityRequest.getDuration())
				.caloriesBurned(activityRequest.getCaloriesBurned())
				.startTime(activityRequest.getStartTime())
				.additionalMetrics(activityRequest.getAdditionalMetrics())
				.build();
		
		Activity savedActivity = activityRepositoty.save(activity);
		
		return mapToResponse(savedActivity);
	}

	private ActivityResponse mapToResponse(Activity savedActivity) {
		
		ActivityResponse activityResponse = new ActivityResponse();
		activityResponse.setId(savedActivity.getId());
		activityResponse.setUserId(savedActivity.getUserId());
		activityResponse.setType(savedActivity.getType());
		activityResponse.setDuration(savedActivity.getDuration());
		activityResponse.setCaloriesBurned(savedActivity.getCaloriesBurned());
		activityResponse.setStartTime(savedActivity.getStartTime());
		activityResponse.setAdditionalMetrics(savedActivity.getAdditionalMetrics());
		activityResponse.setCreatedAt(savedActivity.getCreatedAt());
		activityResponse.setUpdatedAt(savedActivity.getUpdatedAt());
		
		return activityResponse;
	}

	@Override
	public List<ActivityResponse> getUserActivities(String userId) {
		
		List<Activity> activities = activityRepositoty.findByUserId(userId);
		
		return activities.stream()
				.map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public ActivityResponse getActivityById(String activityId) {
		return activityRepositoty.findById(activityId)
				.map(this::mapToResponse)
				.orElseThrow(() -> new ActivityNotFoundException(activityId));
	}

}
