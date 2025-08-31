package com.sayem.trackfit.activity.service;

import java.util.List;

import com.sayem.trackfit.activity.dto.ExcerciseRequest;
import com.sayem.trackfit.activity.dto.FinishedExcerciseRequest;
import com.sayem.trackfit.activity.entity.Excercise;
import com.sayem.trackfit.activity.entity.FinishedExcercise;

public interface IExcerciseService {

	List<Excercise> getAllExcercises();

	Excercise createExcercise(ExcerciseRequest excerciseRequest);
	
	FinishedExcercise completeExcercise(FinishedExcerciseRequest finshiedExcerciseRequest);
	
	List<FinishedExcercise> gtAllFinishedExcercisesByUserId(String userId);

}
