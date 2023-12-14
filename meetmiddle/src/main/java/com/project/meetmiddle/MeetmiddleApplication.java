package com.project.meetmiddle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MeetmiddleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetmiddleApplication.class, args);
	}

}
