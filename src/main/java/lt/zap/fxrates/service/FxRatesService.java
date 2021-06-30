package lt.zap.fxrates.service;

import lt.zap.fxrates.model.FxRates;
import lt.zap.fxrates.repository.CcyAmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FxRatesService {
    @Autowired
    private CcyAmtRepository repository;
    @Autowired
    private FxRatesClient fxRatesClient;

    public FxRates findAll(){
        return fxRatesClient.getRates();
    }
}
