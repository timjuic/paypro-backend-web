package air.found.payprowebbackend.business_logic;

import air.found.payprowebbackend.core.ApiError;
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
        try {
            List<Terminal> terminals = air.found.payprowebbackend.data_access.manual.TerminalRepository.getAllTerminals();
            return ServiceResult.success(terminals);
        }
        catch (Exception ex) {
            return ServiceResult.failure(ApiError.ERR_INVALID_REQUEST);
        }
    }

    public ServiceResult<List<Terminal>> getAllTerminalsForMerchant() {
        return null;
    }
}
