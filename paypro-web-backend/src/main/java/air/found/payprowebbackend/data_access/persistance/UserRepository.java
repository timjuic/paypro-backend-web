package air.found.payprowebbackend.data_access.persistance;

import air.found.payprowebbackend.core.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Integer> {
}
