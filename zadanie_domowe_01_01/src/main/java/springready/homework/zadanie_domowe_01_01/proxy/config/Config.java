package springready.homework.zadanie_domowe_01_01.proxy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

//    @Bean
//    @Profile("!test")
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        return args -> {
//            Quote quote = restTemplate.getForObject(
//                    "http://localhost:8080/api/random", Quote.class);
//            log.info(quote.toString());
//        };
//    }
}
