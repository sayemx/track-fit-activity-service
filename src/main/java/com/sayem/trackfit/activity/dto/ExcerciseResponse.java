package com.sayem.trackfit.activity.dto;

import lombok.Data;


@Data
public class ExcerciseResponse {
	
	private String id;
	
	private String name;
	
	private int duration;
	
	private int calories;
}
