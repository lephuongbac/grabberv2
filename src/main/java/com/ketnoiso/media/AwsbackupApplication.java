package com.ketnoiso.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@EnableScheduling
@Configuration
@ComponentScan
public class AwsbackupApplication {
    public static void main(String[] args) {
        SpringApplication.run(AwsbackupApplication.class, args);
    }
}
