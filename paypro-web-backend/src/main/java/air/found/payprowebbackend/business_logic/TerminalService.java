package air.found.payprowebbackend.business_logic;

import air.found.payprowebbackend.core.ServiceResult;
import air.found.payprowebbackend.core.models.Terminal;
import air.found.payprowebbackend.data_access.persistance.TerminalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerminalService {
    private final TerminalRepository terminalRepository;

    public ServiceResult<List<Terminal>> getAllTerminals() {
        return null;
    }

    public ServiceResult<List<Terminal>> getAllTerminaslForMerchant() {
        return null;
    }
}
