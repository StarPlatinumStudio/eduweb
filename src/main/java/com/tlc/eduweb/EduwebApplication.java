package com.tlc.eduweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EduwebApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduwebApplication.class, args);
    }

}
