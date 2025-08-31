package com.sayem.trackfit.activity.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	
	@Bean
	public DirectExchange activityExchange() {
		return new DirectExchange("fitness.exchange");
	}
	
//	@Bean
//	public Queue activityQueue() {
//		return new Queue("activity.queue", true);
//	}
//	
//	@Bean
//	public Binding activityBinding(Queue activityQueue, DirectExchange activityExchange) {
//		return BindingBuilder.bind(activityQueue).to(activityExchange).with("activity.tracking");
//	}
	
	@Bean
	public Queue excerciseQueue() {
		return new Queue("excercise.queue", true);
	}
	
	@Bean
	public Binding excerciseBinding(Queue excerciseQueue, DirectExchange activityExchange) {
		return BindingBuilder.bind(excerciseQueue).to(activityExchange).with("excercise.tracking");
	}
	
	@Bean
	public MessageConverter jsonMessageConvertor() {
		return new Jackson2JsonMessageConverter();
	}
	
}
