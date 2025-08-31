package com.sayem.trackfit.activity.dto;

import java.time.LocalDateTime;

import com.sayem.trackfit.activity.entity.ActivityType;

import lombok.Data;

@Data
public class FinishedExcerciseRequest {
	
	private String userId;
	
	private ActivityType name;
	
	private int duration;
	
	private int calories;
	
	private String state;
	
}
