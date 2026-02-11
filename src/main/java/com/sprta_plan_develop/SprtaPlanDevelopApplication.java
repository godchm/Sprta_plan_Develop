package com.sprta_plan_develop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SprtaPlanDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprtaPlanDevelopApplication.class, args);
    }

}
