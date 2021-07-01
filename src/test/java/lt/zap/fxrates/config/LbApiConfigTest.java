package lt.zap.fxrates.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LbApiConfigTest {

    @Test
    void getCurrencyHistoryLink() {
        LbApiConfig config = new LbApiConfig();
        config.setCurrencyHistoryLink("http://localhost:8080/");
        String newLink = config.getCurrencyHistoryLink("EUR", "2021-06-02", "2021-07-01");
        assertEquals("http://localhost:8080/?tp=&ccy=EUR&dtFrom=2021-06-02&dtTo=2021-07-01", newLink);
    }
}