package com.aun.playground.spring.boot.ui.beans;

import java.util.Random;
import org.springframework.stereotype.Component;

/**
 * Sample bean to show some data.
 * @author sebastian
 */
@Component
public class SampleBean {

    public String hello() {
        return "Hello Spring Boot Demo";
    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(100 - 1 + 1) + 1;
    }
}
