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

    public void fetchOneQuoterByNumber(String num) throws JsonProcessingException {
        String jsonByNumber = quoteProxy.getQuoteById(num);
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

    public void fetchWithRequestParam(Long id) throws JsonProcessingException {
        String json = quoteProxy.getQuotesWithRequestParam(id);
        if (json != null) {
            Quote quote = mapJsonToQuoterResponse(json);
            log.info(quote.value());
        }
    }

    public void postQuote(String quote) throws JsonProcessingException {
        String json = quoteProxy.postQuote(quote);
        if (json != null) {
            Quote quoteResponse = mapJsonToQuoterResponse(json);
            log.info(quoteResponse.value());
            log.info(quoteResponse.type());
        }
    }

    public void deleteQuote(String id) throws JsonProcessingException {
        String json = quoteProxy.deleteQuote(id);
        if (json != null) {
            Quote quoteResponse = mapJsonToQuoterResponse(json);
            log.info(quoteResponse.value());
        }
    }

    public void getWithHeader() throws JsonProcessingException {
        String json = quoteProxy.getQuoteWithHeader();
        if (json != null) {
            Quote[] allQuotes = mapJsonToAllQuotersResponse(json);
            for (Quote q : allQuotes) {
                log.info("{} {}", q.value(), q.type());
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
