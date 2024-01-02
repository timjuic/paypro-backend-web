package air.found.payprowebbackend.data_access.persistance;

import air.found.payprowebbackend.core.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Integer> {
    Optional<UserAccount> findByEmailAddress(String email);
    Optional<UserAccount> findByEmailAddressAndPassword(String emailAddress, String password);
}
