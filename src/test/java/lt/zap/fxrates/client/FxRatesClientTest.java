package lt.zap.fxrates.client;

import lt.zap.fxrates.config.LbApiConfig;
import lt.zap.fxrates.exception.CurrencyNotFoundException;
import lt.zap.fxrates.exception.MissingCurrencyRatiosException;
import lt.zap.fxrates.model.CurrencyExchangeRates;
import lt.zap.fxrates.model.ExchangeRatio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FxRatesClientTest {

    public static final String CURRENCY = "EUR";
    public static final String DATE_FROM = "2021-06-06";
    public static final String DATE_TO = "2021-07-07";
    public static final String LINK = "http://fxrates/";

    @InjectMocks
    private FxRatesClient fxRatesClient;

    @Mock
    private LbApiConfig config;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void getRatesWithNullResponseTest() {
        when(config.getCurrencyListLink()).thenReturn(LINK);
        when(restTemplate.getForObject(LINK, CurrencyExchangeRates.class)).thenReturn(null);

        assertThrows(MissingCurrencyRatiosException.class, () -> fxRatesClient.getRates());
    }

    @Test
    void getRatesTest() {
        List<CurrencyExchangeRates.ExchangeRate> result = new ArrayList<>();
        CurrencyExchangeRates currencyExchangeRates = new CurrencyExchangeRates();
        currencyExchangeRates.setRateList(result);

        List<ExchangeRatio> exchangeRatios = new ArrayList<>();
        ExchangeRatio eurRatio = new ExchangeRatio();
        eurRatio.setCurrency("EUR");
        eurRatio.setAmount(1.0);
        ExchangeRatio usdRatio = new ExchangeRatio();
        usdRatio.setCurrency("USD");
        usdRatio.setAmount(0.8);
        exchangeRatios.add(eurRatio);
        exchangeRatios.add(usdRatio);

        List<ExchangeRatio> exchangeRatiosWithoutEur = new ArrayList<>();
        exchangeRatiosWithoutEur.add(usdRatio);

        CurrencyExchangeRates.ExchangeRate testRate = new CurrencyExchangeRates.ExchangeRate("2021-01-01", exchangeRatios);
        result.add(testRate);

        when(config.getCurrencyListLink()).thenReturn(LINK);
        when(restTemplate.getForObject(LINK, CurrencyExchangeRates.class)).thenReturn(currencyExchangeRates);

        assertEquals(exchangeRatiosWithoutEur, fxRatesClient.getRates());
    }

    @Test
    void getRatesWithOnlyEurTest() {
        List<CurrencyExchangeRates.ExchangeRate> result = new ArrayList<>();
        CurrencyExchangeRates currencyExchangeRates = new CurrencyExchangeRates();
        currencyExchangeRates.setRateList(result);

        List<ExchangeRatio> exchangeRatios = new ArrayList<>();
        ExchangeRatio eurRatio = new ExchangeRatio();
        eurRatio.setCurrency("EUR");
        eurRatio.setAmount(1.0);
        exchangeRatios.add(eurRatio);

        CurrencyExchangeRates.ExchangeRate testRate = new CurrencyExchangeRates.ExchangeRate("2021-01-01", exchangeRatios);
        result.add(testRate);

        when(config.getCurrencyListLink()).thenReturn(LINK);
        when(restTemplate.getForObject(LINK, CurrencyExchangeRates.class)).thenReturn(currencyExchangeRates);

        assertThrows(CurrencyNotFoundException.class, () -> fxRatesClient.getRates());
    }

    @Test
    void getRatesForCurrencyTest() {
        List<CurrencyExchangeRates.ExchangeRate> result = new ArrayList<>();
        CurrencyExchangeRates currencyExchangeRates = new CurrencyExchangeRates();
        currencyExchangeRates.setRateList(result);
        CurrencyExchangeRates.ExchangeRate testRate = new CurrencyExchangeRates.ExchangeRate("2020-01-01", Collections.emptyList());
        result.add(testRate);
        when(config.getCurrencyHistoryLink(CURRENCY, DATE_FROM, DATE_TO)).thenReturn(LINK);
        when(restTemplate.getForObject(LINK, CurrencyExchangeRates.class)).thenReturn(currencyExchangeRates);

        assertEquals(result, fxRatesClient.getRatesForCurrency(CURRENCY, DATE_FROM, DATE_TO));
    }

    @Test
    void getRatesForCurrencyWithNullResponseTest() {
        when(config.getCurrencyHistoryLink(CURRENCY, DATE_FROM, DATE_TO)).thenReturn(LINK);
        when(restTemplate.getForObject(LINK, CurrencyExchangeRates.class)).thenReturn(null);

        assertThrows(MissingCurrencyRatiosException.class, () -> fxRatesClient.getRatesForCurrency(CURRENCY, DATE_FROM, DATE_TO));
    }
}