package lt.zap.fxrates.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FxRates", namespace = "http://www.lb.lt/WebServices/FxRates")
public class CurrencyExchangeRates {

    @XmlElement(name = "FxRate", namespace = "http://www.lb.lt/WebServices/FxRates")
    private List<ExchangeRate> rateList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "FxRate")
    public static class ExchangeRate {
        @XmlElement(name="Dt", namespace = "http://www.lb.lt/WebServices/FxRates")
        private String date;
        @XmlElement(name = "CcyAmt", namespace = "http://www.lb.lt/WebServices/FxRates")
        private List<ExchangeRatio> exchangeRatio;
    }
}
