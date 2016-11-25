package com.spacecorpshandbook.ostium.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
@RestController

@RequestMapping("/")
public class CancellationApp {

    @RequestMapping(value = "appointment", method = RequestMethod.DELETE)
    public String cancelAppointment() {
        return "Hello world";
    }

    public static void main(String[] args) {

        new SpringApplicationBuilder(CancellationApp.class).web(true).run(args);
    }

}
