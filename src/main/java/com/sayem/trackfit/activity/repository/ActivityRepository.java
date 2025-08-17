package com.sayem.trackfit.activity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sayem.trackfit.activity.entity.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String> {
	
	List<Activity> findByUserId(String userId);

}
