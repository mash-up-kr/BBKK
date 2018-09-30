package org.seoul.kk.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class HealthCheckerController {

    private HealthCheckDto healthCheckDto;

    @Autowired
    public HealthCheckerController(ResourceLoader resourceLoader) {
        Resource resource = resourceLoader.getResource("classpath:health-check.txt");
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            healthCheckDto = new HealthCheckDto();
            healthCheckDto.deployDate = bufferedReader.readLine().split(":")[1];
            healthCheckDto.deployVersion = bufferedReader.readLine().split(":")[1];
            healthCheckDto.distributor = bufferedReader.readLine().split(":")[1];

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Getter @Setter
    class HealthCheckDto {
        String deployDate;
        String deployVersion;
        String distributor;
    }

    @GetMapping(value = "/v1/health-checker", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HealthCheckDto healthChecker() {
        return healthCheckDto;
    }

}
