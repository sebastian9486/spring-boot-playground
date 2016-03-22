package com.aun.playground.spring.boot.ui.beans;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class SampleBean {

	public String hello() {
		return "hello";
	}

	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(100 - 1 + 1) + 1;
	}
}
