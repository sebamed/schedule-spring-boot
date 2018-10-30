package com.mudri.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * Schedule Application entry point
 * @author sebamed
 */

@EnableAsync
@SpringBootApplication
public class ScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}

}
