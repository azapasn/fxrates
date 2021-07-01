package lt.zap.fxrates.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lt.zap.fxrates.exception.CurrencyNotFoundException;
import lt.zap.fxrates.exception.MissingCurrencyRatiosException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CcyAmt")
public class ExchangeRatio {

    @Id
    @NonNull
    @XmlElement(name = "Ccy", namespace = "http://www.lb.lt/WebServices/FxRates")
    private String currency;

    @XmlElement(name = "Amt", namespace = "http://www.lb.lt/WebServices/FxRates")
    private Double amount;

    public static ExchangeRatio filterRequiredCurrency(CurrencyExchangeRates.ExchangeRate exchangePair) {
        return exchangePair
                .getExchangeRatio()
                .stream()
                .filter(item -> !item.getCurrency().equals("EUR"))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);
    }
}
