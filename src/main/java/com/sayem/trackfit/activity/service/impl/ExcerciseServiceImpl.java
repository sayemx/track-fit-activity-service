package com.sayem.trackfit.activity.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sayem.trackfit.activity.dto.ExcerciseRequest;
import com.sayem.trackfit.activity.dto.FinishedExcerciseRequest;
import com.sayem.trackfit.activity.entity.Excercise;
import com.sayem.trackfit.activity.entity.FinishedExcercise;
import com.sayem.trackfit.activity.exception.ExcerciseAlreadyExist;
import com.sayem.trackfit.activity.repository.ExcerciseRepository;
import com.sayem.trackfit.activity.repository.FinishedExcerciseRepository;
import com.sayem.trackfit.activity.service.IExcerciseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcerciseServiceImpl implements IExcerciseService{
	
	private final ExcerciseRepository excerciseRepository;
	private final FinishedExcerciseRepository finishedExcerciseRepository;
	
	private final RabbitTemplate rabbitTemplate;
	
	
	@Override
	public List<Excercise> getAllExcercises() {
		return excerciseRepository.findAll();
	}

	@Override
	public Excercise createExcercise(ExcerciseRequest excerciseRequest) {
		
		Optional<Excercise> existingExcercise = excerciseRepository.findByName(excerciseRequest.getName());
		if(existingExcercise.isPresent()) {
			throw new ExcerciseAlreadyExist(excerciseRequest.getName() + "");
		}
		Excercise excercise = new Excercise();
		
		excercise.setName(excerciseRequest.getName());
		excercise.setDuration(excerciseRequest.getDuration());
		excercise.setCalories(excerciseRequest.getCalories());
		
		Excercise savedExcercise = excerciseRepository.save(excercise);
		
		return savedExcercise;
	}

	@Override
	public FinishedExcercise completeExcercise(FinishedExcerciseRequest finshiedExcerciseRequest) {
		
		FinishedExcercise finishedExcercise = new FinishedExcercise();
		finishedExcercise.setName(finshiedExcerciseRequest.getName());
		finishedExcercise.setCalories(finshiedExcerciseRequest.getCalories());
		finishedExcercise.setDuration(finshiedExcerciseRequest.getDuration());
		finishedExcercise.setState(finshiedExcerciseRequest.getState());
		finishedExcercise.setUserId(finshiedExcerciseRequest.getUserId());
		
		FinishedExcercise savedFExcercise = finishedExcerciseRepository.save(finishedExcercise);
		
		try {
			rabbitTemplate.convertAndSend("fitness.exchange", "excercise.tracking", savedFExcercise);
			log.info("Pushed the finished excercise to the queue: {}", savedFExcercise);
		} catch(Exception e) {
			log.error("Failed to publish finished excercise to RabbitMQ: {}", e);
		}
		
		return savedFExcercise;
	}

	@Override
	public List<FinishedExcercise> gtAllFinishedExcercisesByUserId(String userId) {
		
		List<FinishedExcercise> finishedExcercises = finishedExcerciseRepository.findByUserId(userId);
		
		return finishedExcercises;
	}

}
