package lt.zap.fxrates.web;

import lombok.AllArgsConstructor;
import lt.zap.fxrates.service.FxRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class FxRatesController {
    @Autowired
    private FxRatesService fxRatesService;

    @RequestMapping("/getcurrentfxrates")
    public void getCurrentFxRates() throws URISyntaxException {
        fxRatesService.updateCurrencyRatios();
    }
}
