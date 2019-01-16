package com.levo.tdd.controller;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestContext {
	
	@Bean
	private ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

}
