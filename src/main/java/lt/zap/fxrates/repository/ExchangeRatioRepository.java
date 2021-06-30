package lt.zap.fxrates.repository;

import lt.zap.fxrates.model.ExchangeRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRatioRepository extends JpaRepository<ExchangeRatio, String> {
}
