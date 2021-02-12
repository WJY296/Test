package com.wjy;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableHasor
@EnableHasorWeb
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
