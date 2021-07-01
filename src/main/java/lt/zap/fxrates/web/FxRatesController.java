package lt.zap.fxrates.web;

import lombok.AllArgsConstructor;
import lt.zap.fxrates.service.FxRatesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
public class FxRatesController {

    private final FxRatesService fxRatesService;

    @RequestMapping("/")
    public String getCurrentFxRates(Model model){
        model.addAttribute("exList", fxRatesService.getCurrentFxRates());
        return "index";
    }
}
