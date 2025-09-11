package springready.homework.zadanie_domowe_01_01.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import springready.homework.zadanie_domowe_01_01.proxy.quote.Quote;
import springready.homework.zadanie_domowe_01_01.proxy.quote.QuoteProxy;

@Log4j2
@Service
public class QuoterService {

    private final QuoteProxy quoteProxy;

    public QuoterService(QuoteProxy quoteProxy) {
        this.quoteProxy = quoteProxy;
    }

    public void fetchQuote() throws JsonProcessingException {
        String json = quoteProxy.makeRequest();
        String jsonByNumber = quoteProxy.makeRequestByNumberQuoter();
        if (json != null) {
            Quote quote = mapJsonToQuoteResponse(json);
            log.info(quote.value());
        }
        if (jsonByNumber != null) {
            Quote quoteByNumber = mapJsonToQuoteResponse(jsonByNumber);
            log.info(quoteByNumber.value());
        }
    }

    private static Quote mapJsonToQuoteResponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Quote.class);
    }
}
