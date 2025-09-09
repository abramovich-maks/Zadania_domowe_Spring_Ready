package springready.homework.zadanie_domowe_01_01;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import springready.homework.zadanie_domowe_01_01.proxy.quote.QuoteProxy;

@Log4j2
@SpringBootApplication
public class ZadanieDomowe0101Application {

    private final QuoteProxy quoteClient;

    public ZadanieDomowe0101Application(QuoteProxy quoteClient) {
        this.quoteClient = quoteClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZadanieDomowe0101Application.class, args);
    }


    @EventListener(ApplicationStartedEvent.class)
    public void run() throws JsonProcessingException {

        quoteClient.toEntity();
    }
}
