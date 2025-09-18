package com.zadanie_domowe;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ZadanieDomowe0202vRestTApplication {

    private final MainApplicationRunner runner;

    public ZadanieDomowe0202vRestTApplication(MainApplicationRunner runner) {
        this.runner = runner;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZadanieDomowe0202vRestTApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() throws JsonProcessingException {
        runner.start("abramovich-maks");
    }
}
