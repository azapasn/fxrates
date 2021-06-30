package lt.zap.fxrates.repository;

import lt.zap.fxrates.model.CcyAmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CcyAmtRepository extends JpaRepository<CcyAmt, String> {
}
