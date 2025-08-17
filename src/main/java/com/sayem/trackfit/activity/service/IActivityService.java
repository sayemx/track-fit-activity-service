package com.sayem.trackfit.activity.service;

import java.util.List;

import com.sayem.trackfit.activity.dto.ActivityRequest;
import com.sayem.trackfit.activity.dto.ActivityResponse;

public interface IActivityService {

	ActivityResponse trackActivity(ActivityRequest activityRequest);

	List<ActivityResponse> getUserActivities(String userId);

	ActivityResponse getActivityById(String activityId);

}
