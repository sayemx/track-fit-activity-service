package com.sayem.trackfit.activity.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sayem.trackfit.activity.entity.ActivityType;
import com.sayem.trackfit.activity.entity.Excercise;

public interface ExcerciseRepository extends MongoRepository<Excercise, String> {

//	Excercise findByName(String name);

	Optional<Excercise> findByName(ActivityType name);


}
