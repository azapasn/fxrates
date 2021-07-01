package lt.zap.fxrates.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.zap.fxrates.config.LbApiConfig;
import lt.zap.fxrates.exception.MissingCurrencyRatiosException;
import lt.zap.fxrates.model.CurrencyExchangeRates;
import lt.zap.fxrates.model.ExchangeRatio;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class FxRatesClient {

    private final LbApiConfig config;

    private final RestTemplate restTemplate;

    public List<ExchangeRatio> getRates(){
        List<CurrencyExchangeRates.ExchangeRate> rateList = Optional
                .ofNullable(restTemplate
                        .getForObject(config.getCurrencyListLink(), CurrencyExchangeRates.class))
                .orElseThrow(MissingCurrencyRatiosException::new)
                .getRateList();
        log.info("Got [" + rateList.size() + "] exchange rates from external LB api");
        return rateList
                .stream()
                .map(ExchangeRatio::filterRequiredCurrency)
                .collect(Collectors.toList());
    }

    public List<CurrencyExchangeRates.ExchangeRate> getRatesForCurrency(String currency, String dateFrom, String dateTo){
        List<CurrencyExchangeRates.ExchangeRate> rateList = Optional
                .ofNullable(restTemplate
                        .getForObject(config.getCurrencyHistoryLink(currency, dateFrom, dateTo), CurrencyExchangeRates.class))
                .orElseThrow(MissingCurrencyRatiosException::new)
                .getRateList();
        log.info("Got [" + rateList.size() + "] exchange rates for currency " + currency + " from external LB api");
        return rateList;
    }
}

