package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo {
    public static void main(String[] args) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        SpringApplication.run(Demo.class, args);
    }
}