package com.example.staydiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StayDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StayDiscoveryApplication.class, args);
    }

}
