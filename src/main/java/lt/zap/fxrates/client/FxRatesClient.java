package lt.zap.fxrates.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.zap.fxrates.config.LbApiConfig;
import lt.zap.fxrates.exception.MissingCurrencyRatiosException;
import lt.zap.fxrates.model.CurrencyExchangeRates;
import lt.zap.fxrates.model.ExchangeRatio;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class FxRatesClient {

    private final LbApiConfig config;

    private final RestTemplate restTemplate;

    public List<ExchangeRatio> getRates() throws URISyntaxException {
        try {
            List<CurrencyExchangeRates.ExchangeRate> rateList = Optional
                    .ofNullable(restTemplate
                            .getForObject(new URI(config.getCurrencyListLink()), CurrencyExchangeRates.class))
                    .orElseThrow(MissingCurrencyRatiosException::new)
                    .getRateList();
            log.info("Got [" + rateList.size() + "] exchange rates from external LB api");
            return rateList
                    .stream()
                    .map(ExchangeRatio::filterRequiredCurrency)
                    .collect(Collectors.toList());
        } catch (URISyntaxException e) {
            log.error("URI syntax exception: " + config.getCurrencyListLink());
            throw e;
        }
    }
    public List<CurrencyExchangeRates.ExchangeRate> getRatesForCurrency(String currency, String dateFrom, String dateTo) throws URISyntaxException {
        try {
            List<CurrencyExchangeRates.ExchangeRate> rateList = Optional
                    .ofNullable(restTemplate
                            .getForObject(new URI(config.getCurrencyHistoryLink() + "?tp=&ccy=" + currency + "&dtFrom=" + dateFrom + "&dtTo=" + dateTo), CurrencyExchangeRates.class))
                    .orElseThrow(MissingCurrencyRatiosException::new)
                    .getRateList();
            log.info("Got [" + rateList.size() + "] exchange rates for currency " + currency + " from external LB api");
            return rateList;
        } catch (URISyntaxException e) {
            log.error("URI syntax exception: " + config.getCurrencyListLink());
            throw e;
        }
    }
}

