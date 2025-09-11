package springready.homework.zadanie_domowe_01_01.proxy.quote;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    public String getRandomQuote() {
        URI uriBuilder = buildRandomQuoteUri();
        return makeGetRequestQuote(uriBuilder);
    }

    public String getQuoteById(String id) {
        URI uriBuilder = buildQuoteByIdUri(id);
        return makeGetRequestQuote(uriBuilder);
    }

    public String getAllQuotes() {
        URI uriBuilder = buildAllQuotesUri();
        return makeGetRequestQuote(uriBuilder);
    }

    public String getQuotesWithRequestParam(Long id) {
        URI uriBuilder = buildQuoteWithRequestParam(id);
        return makeGetRequestQuote(uriBuilder);
    }

    public String postQuote(String quote) {
        URI uriBuilder = buildOneQuoteUri();
        return makePostRequestQuote(uriBuilder,quote);
    }

    public String deleteQuote(String id) {
        URI uriBuilder = buildDeleteQuoteUri(id);
        return makeDeleteRequestQuote(uriBuilder);
    }

    public String getQuoteWithHeader(){
        URI uri = buildGetAllWithHeaderQuoteUri();
        return makeGetRequestQuoteWithHeader(uri);
    }

    private String makeGetRequestQuote(URI uri) {
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    uri,
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

    private String makePostRequestQuote(URI uri, String quote) {
        QuoteRequest quoteRequest = new QuoteRequest(quote);
        HttpEntity<QuoteRequest> objectHttpEntity = new HttpEntity<>(quoteRequest);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    objectHttpEntity,
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

    private String makeDeleteRequestQuote(URI uri) {
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    uri,
                    HttpMethod.DELETE,
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

    private String makeGetRequestQuoteWithHeader(URI uri) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("requestId", "AAA");
        HttpEntity<QuoteRequest> entity = new HttpEntity<>(httpHeaders);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
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

    private URI buildRandomQuoteUri() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/api/random").build().toUri();
    }

    private URI buildQuoteByIdUri(String number) {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/api")
                .path("/" + number).build().toUri();
    }

    private URI buildAllQuotesUri() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/api").build().toUri();
    }

    private URI buildQuoteWithRequestParam(Long id) {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/apiWithRequestParam")
                .queryParam("id", id).build().toUri();
    }

    private URI buildOneQuoteUri() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/api/quote")
                .build().toUri();
    }

    private URI buildDeleteQuoteUri(String id) {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/api/quote/" + id).build().toUri();
    }

    private URI buildGetAllWithHeaderQuoteUri() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/apiWithHeader").build().toUri();
    }
}