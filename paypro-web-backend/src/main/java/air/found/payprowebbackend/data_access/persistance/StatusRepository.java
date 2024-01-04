package air.found.payprowebbackend.data_access.persistance;

import air.found.payprowebbackend.core.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
