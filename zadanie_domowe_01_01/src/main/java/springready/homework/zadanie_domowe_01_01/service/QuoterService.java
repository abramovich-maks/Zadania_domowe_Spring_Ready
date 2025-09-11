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

    public void fetchOneQuoterByNumber() throws JsonProcessingException {
        String jsonByNumber = quoteProxy.getQuoteById("1");
        if (jsonByNumber != null) {
            Quote quoteByNumber = mapJsonToQuoterResponse(jsonByNumber);
            log.info(quoteByNumber.value());
        }
    }

    public void fetchRandomQuoter() throws JsonProcessingException {
        String json = quoteProxy.getRandomQuote();
        if (json != null) {
            Quote quote = mapJsonToQuoterResponse(json);
            log.info(quote.value());
        }
    }

    public void fetchAllQuoters() throws JsonProcessingException {
        String jsonAll = quoteProxy.getAllQuotes();
        if (jsonAll != null) {
            Quote[] allQuotes = mapJsonToAllQuotersResponse(jsonAll);
            for (Quote q : allQuotes) {
                log.info(q.value());
            }
        }
    }

    private static Quote mapJsonToQuoterResponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Quote.class);
    }

    private static Quote[] mapJsonToAllQuotersResponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Quote[].class);
    }

}
