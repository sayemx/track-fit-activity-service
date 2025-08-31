package com.sayem.trackfit.activity.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "finishedExcercises")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinishedExcercise {
	
	@Id
	private String id;
	
	private String userId;
	
	private ActivityType name;
	
	private int duration;
	
	private int calories;
	
	@CreatedDate
	private LocalDateTime date;
	
	private String state;
}
