package com.example.case1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Case1Application {

    public static void main(String[] args) {
        SpringApplication.run(Case1Application.class, args);
    }

}
