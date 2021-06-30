package lt.zap.fxrates.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.zap.fxrates.client.FxRatesClient;
import lt.zap.fxrates.repository.ExchangeRatioRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

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
}
