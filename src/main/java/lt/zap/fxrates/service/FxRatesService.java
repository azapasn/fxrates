package lt.zap.fxrates.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.zap.fxrates.client.FxRatesClient;
import lt.zap.fxrates.model.CurrencyExchangeRates;
import lt.zap.fxrates.model.ExchangeRatio;
import lt.zap.fxrates.repository.ExchangeRatioRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class FxRatesService {

    private final ExchangeRatioRepository repository;

    private final FxRatesClient fxRatesClient;

    @PostConstruct
    public void updateCurrencyRatios(){
        repository.saveAll(fxRatesClient.getRates());
        log.info("Currency ratios updated");

    }
    public List<CurrencyExchangeRates.ExchangeRate> getCurrencyRatesHistory(String currency, String dateFrom, String dateTo){
        if(Objects.isNull(dateFrom)){
            dateFrom = LocalDate.now().minusMonths(1).toString();
        }
        if(Objects.isNull(dateTo)){
            dateTo = LocalDate.now().toString();
        }
        dateTo = preventFutureDate(dateTo);
        dateFrom = preventFutureDate(dateFrom);
        return fxRatesClient.getRatesForCurrency(currency, dateFrom, dateTo);
    }
    private String preventFutureDate(String date){
        if(LocalDate.parse(date).isAfter(LocalDate.now())){
            return LocalDate.now().toString();
        }
        return date;
    }

    public List<ExchangeRatio> getCurrentFxRates() {
        return repository.findAll();
    }
}
