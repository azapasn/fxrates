package lt.zap.fxrates.web;

import lt.zap.fxrates.model.FxRates;
import lt.zap.fxrates.service.FxRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FxRatesController {
    @Autowired
    private FxRatesService fxRatesService;

    @RequestMapping("/getcurrentfxrates")
    public FxRates getCurrentFxRates(){
        return fxRatesService.findAll();
    }
}
