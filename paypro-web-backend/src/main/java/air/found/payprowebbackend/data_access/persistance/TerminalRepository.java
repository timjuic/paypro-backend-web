package air.found.payprowebbackend.data_access.persistance;

import air.found.payprowebbackend.core.models.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {
}
