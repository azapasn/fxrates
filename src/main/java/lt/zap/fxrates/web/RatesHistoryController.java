package lt.zap.fxrates.web;

import lombok.AllArgsConstructor;
import lt.zap.fxrates.model.CurrencyExchangeRates;
import lt.zap.fxrates.service.FxRatesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@AllArgsConstructor
public class RatesHistoryController {

    private final FxRatesService fxRatesService;

    @GetMapping("/getcurrencyfxrates")
    public String getCurrencyFxRates(
            Model model,
            @PathParam("ccy") String ccy,
            @PathParam("dtFrom") String dtFrom,
            @PathParam("dtTo") String dtTo){
        List<CurrencyExchangeRates.ExchangeRate> response = fxRatesService.getCurrencyRatesHistory(ccy, dtFrom, dtTo);
        model.addAttribute("dtFrom", dtFrom);
        model.addAttribute("dtTo", dtTo);
        model.addAttribute("ratesHistoryList", response);
        return "rates";
    }
}
