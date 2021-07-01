package lt.zap.fxrates.service;

import lt.zap.fxrates.client.FxRatesClient;
import lt.zap.fxrates.model.CurrencyExchangeRates;
import lt.zap.fxrates.model.ExchangeRatio;
import lt.zap.fxrates.repository.ExchangeRatioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FxRatesServiceTest {

    private static final ExchangeRatio USD_RATIO = new ExchangeRatio("USD", 0.5);
    private static final ExchangeRatio RUB_RATIO = new ExchangeRatio("RUB", 1.5);

    @InjectMocks
    private FxRatesService fxRatesService;

    @Mock
    private ExchangeRatioRepository repository;

    @Mock
    private FxRatesClient fxRatesClient;

    @Test
    void getCurrencyRatesHistoryWithParamsPresentTest(){
        List<CurrencyExchangeRates.ExchangeRate> result = new ArrayList<>();
        CurrencyExchangeRates.ExchangeRate testRate = new CurrencyExchangeRates.ExchangeRate("2021-01-01", Collections.emptyList());
        result.add(testRate);

        String currency = "EUR";
        String dateFrom = "2021-01-01";
        String dateTo = "2021-02-02";

        when(fxRatesClient.getRatesForCurrency(currency, dateFrom, dateTo)).thenReturn(result);

        assertEquals(result, fxRatesService.getCurrencyRatesHistory(currency, dateFrom, dateTo));
    }
    @Test
    void getCurrencyRatesHistoryWithNullParamsTest(){
        List<CurrencyExchangeRates.ExchangeRate> result = new ArrayList<>();
        CurrencyExchangeRates.ExchangeRate testRate = new CurrencyExchangeRates.ExchangeRate("2021-01-01", Collections.emptyList());
        result.add(testRate);

        String currency = "EUR";
        String dateFrom = LocalDate.now().minusMonths(1).toString();
        String dateTo = LocalDate.now().toString();

        when(fxRatesClient.getRatesForCurrency(currency, dateFrom, dateTo)).thenReturn(result);

        assertEquals(result, fxRatesService.getCurrencyRatesHistory(currency, null, null));
    }
    @Test
    void getCurrencyRatesHistoryWithFutureDateParamsTest(){
        List<CurrencyExchangeRates.ExchangeRate> result = new ArrayList<>();
        CurrencyExchangeRates.ExchangeRate testRate = new CurrencyExchangeRates.ExchangeRate("2021-01-01", Collections.emptyList());
        result.add(testRate);

        String currency = "EUR";
        String dateFrom = LocalDate.now().toString();
        String dateTo = LocalDate.now().toString();

        when(fxRatesClient.getRatesForCurrency(currency, dateFrom, dateTo)).thenReturn(result);

        assertEquals(result, fxRatesService.getCurrencyRatesHistory(currency,
                LocalDate.now().plusDays(1).toString(),
                LocalDate.now().plusMonths(1).toString()));
    }

    @Test
    void updateCurrencyRatiosTest(){
        List<ExchangeRatio> clientResult = new ArrayList<>();
        clientResult.add(USD_RATIO);
        clientResult.add(RUB_RATIO);
        when(fxRatesClient.getRates()).thenReturn(clientResult);

        when(repository.saveAll(clientResult)).thenReturn(clientResult);

        fxRatesService.updateCurrencyRatios();

        verify(repository).saveAll(eq(clientResult));
    }

}