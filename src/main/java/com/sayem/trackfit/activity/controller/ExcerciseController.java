package com.sayem.trackfit.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayem.trackfit.activity.dto.ExcerciseRequest;
import com.sayem.trackfit.activity.dto.FinishedExcerciseRequest;
import com.sayem.trackfit.activity.entity.Excercise;
import com.sayem.trackfit.activity.entity.FinishedExcercise;
import com.sayem.trackfit.activity.service.IExcerciseService;

@RestController
@RequestMapping("/api/excercises")
public class ExcerciseController {
	
	@Autowired
	private IExcerciseService excerciseService;;
	

	
	@GetMapping
	public ResponseEntity<List<Excercise>> getAllExcercises() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(excerciseService.getAllExcercises());
	}
	
	@PostMapping
	public ResponseEntity<Excercise> createExcercise(@RequestBody ExcerciseRequest excerciseRequest) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(excerciseService.createExcercise(excerciseRequest));
	}
	
	@PostMapping("/complete")
	public ResponseEntity<FinishedExcercise> completeExcercise(@RequestBody FinishedExcerciseRequest finishedExcerciseRequest) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(excerciseService.completeExcercise(finishedExcerciseRequest));
	}
	
	@GetMapping("/complete/{userId}")
	public ResponseEntity<List<FinishedExcercise>> getCompletedExcercises(@PathVariable String userId) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(excerciseService.gtAllFinishedExcercisesByUserId(userId));
	}
	

	
}
