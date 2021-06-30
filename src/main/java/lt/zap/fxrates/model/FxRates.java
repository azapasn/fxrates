package lt.zap.fxrates.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FxRates")
public class FxRates {
    @XmlElement(name="FxRate")
    private List<FxRate> fxRate;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class FxRate {

        @XmlElement(name="Tp")
        private String tp;
        @XmlElement(name="Dt")
        private Date dt;

        @XmlElement(name="CcyAmt")
        private List<CcyAmt> ccyAmt;
    }
}