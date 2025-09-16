package com.zadanie_domowe.github;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class GithubFeignConfig {
    @Bean
    public RequestInterceptor defaultHeaders() {
        return tpl -> {
            tpl.header("Accept", "application/json");
            tpl.header("User-Agent", "zadanie-domowe/1.0");
            String token = System.getenv("GITHUB_TOKEN");
            if (token != null && !token.isBlank()) {
                tpl.header("Authorization", "Bearer " + token);
            }
        };
    }
}

