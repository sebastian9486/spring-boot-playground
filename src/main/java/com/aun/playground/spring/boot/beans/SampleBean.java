package com.aun.playground.spring.boot.beans;

import org.springframework.stereotype.Component;

@Component
public class SampleBean {

	public String hello() {
		return "Hello World!";
	}
}
