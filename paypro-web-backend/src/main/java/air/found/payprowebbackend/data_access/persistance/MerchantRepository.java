package air.found.payprowebbackend.data_access.persistance;

import air.found.payprowebbackend.core.models.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
}
