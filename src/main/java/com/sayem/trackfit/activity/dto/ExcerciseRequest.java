package com.sayem.trackfit.activity.dto;

import com.sayem.trackfit.activity.entity.ActivityType;

import lombok.Data;

@Data
public class ExcerciseRequest {
	
	private ActivityType name;
	
	private int duration;
	
	private int calories;
	
}
