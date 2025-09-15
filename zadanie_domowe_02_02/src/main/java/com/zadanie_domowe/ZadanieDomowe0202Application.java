package com.zadanie_domowe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ZadanieDomowe0202Application {

    public static void main(String[] args) {
        SpringApplication.run(ZadanieDomowe0202Application.class, args);
    }

}
