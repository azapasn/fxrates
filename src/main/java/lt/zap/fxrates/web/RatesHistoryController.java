package lt.zap.fxrates.web;

import lt.zap.fxrates.model.CurrencyExchangeRates;
import lt.zap.fxrates.service.FxRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class RatesHistoryController {

    @Autowired
    private FxRatesService fxRatesService;

    @GetMapping("/getcurrencyfxrates")
    public List<CurrencyExchangeRates.ExchangeRate> getCurrencyFxRates(
            //Model model,
            @PathParam("ccy") String ccy,
            @PathParam("dtFrom") String dtFrom,
            @PathParam("dtTo") String dtTo) throws URISyntaxException {
        return fxRatesService.getCurrencyRatesHistory(ccy, dtFrom, dtTo);
//        model.addAttribute("dtFrom", dtFrom);
//        model.addAttribute("dtTo", dtTo);
//        model.addAttribute("ratesHistoryList", response);
//        return "rates";
    }
}