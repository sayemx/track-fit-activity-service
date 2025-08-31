package com.sayem.trackfit.activity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "excercises")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Excercise {
	
	@Id
	private String id;
	
	private ActivityType name;
	
	private int duration;
	
	private int calories;
	
}
