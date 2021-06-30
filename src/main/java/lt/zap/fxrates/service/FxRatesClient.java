package lt.zap.fxrates.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.zap.fxrates.config.LbApiConfig;
import lt.zap.fxrates.model.FxRates;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@AllArgsConstructor
@Component
@Slf4j
public class FxRatesClient {

    private final LbApiConfig config;

    private final RestTemplate restTemplate;

    public FxRates getRates(){
        String url ="http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=";
        try {
            FxRates response = restTemplate.getForObject(new URI(url), FxRates.class);
            return response;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
