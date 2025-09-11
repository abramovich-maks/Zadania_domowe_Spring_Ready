package springready.homework.zadanie_domowe_01_01.proxy.quote;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j2
public class QuoteProxy {


    private final RestTemplate restTemplate;

    public QuoteProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${quote-server.service.url}")
    String url;

    @Value("${quote-server.service.port}")
    String port;

    public String makeRequest() {
        UriComponentsBuilder uriBuilder = getUriBuilderRandomQuote();
        return getString(uriBuilder);
    }

    public String makeRequestByNumberQuoter() {
        UriComponentsBuilder uriBuilder = getUriBuilderByNumberQuoter("5");
        return getString(uriBuilder);
    }

    private String getString(UriComponentsBuilder uriBuilder) {
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    uriBuilder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String.class
            );
            return exchange.getBody();
        } catch (RestClientResponseException exception) {
            log.info("Bad request: " + exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.info(exception.getMessage());
        }
        return null;
    }

    private UriComponentsBuilder getUriBuilderRandomQuote() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/api/random");
    }

    private UriComponentsBuilder getUriBuilderByNumberQuoter(String number) {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/api")
                .path("/"+number);
    }
}