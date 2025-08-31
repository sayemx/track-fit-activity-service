package com.sayem.trackfit.activity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sayem.trackfit.activity.entity.FinishedExcercise;

public interface FinishedExcerciseRepository extends MongoRepository<FinishedExcercise, String> {
	
	List<FinishedExcercise> findByUserId(String userId);

}
