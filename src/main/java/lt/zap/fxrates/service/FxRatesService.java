package lt.zap.fxrates.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.zap.fxrates.client.FxRatesClient;
import lt.zap.fxrates.model.CurrencyExchangeRates;
import lt.zap.fxrates.repository.ExchangeRatioRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class FxRatesService {

    private final ExchangeRatioRepository repository;

    private final FxRatesClient fxRatesClient;

    public void updateCurrencyRatios() throws URISyntaxException{
        repository.saveAll(fxRatesClient.getRates());
        log.info("Currency ratios updated");

    }
    public List<CurrencyExchangeRates.ExchangeRate> getCurrencyRatesHistory(String currency, String dateFrom, String dateTo) throws URISyntaxException {
        if(Objects.isNull(dateFrom)){
            dateFrom = LocalDate.now().minusMonths(1).toString();
        }
        if(Objects.isNull(dateTo)){
            dateTo = LocalDate.now().toString();
        }

        return fxRatesClient.getRatesForCurrency(currency, dateFrom, dateTo);
    }
}
