package com.edu.microservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.microservice.limitsservice.bean.LimitConfiguration;
import com.edu.microservice.limitsservice.config.Configuration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


//Controller method.
@RestController
public class LimitsConfigController {
//atuowired by name
	@Autowired
	private Configuration configuration;
	
	@GetMapping(path = "/limits")
	public LimitConfiguration retrieveConfiglimits() {
		
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	@GetMapping(path = "/foult-tolerance/limits")
	@HystrixCommand(fallbackMethod= "fallbackMethod")
	public LimitConfiguration retrieveFaoultConfiglimits() {
		
		throw new RuntimeException("this is fault toulerance");
	}
	
     public LimitConfiguration fallbackMethod() {
		
		return new LimitConfiguration(9999, 9);
	}
}
