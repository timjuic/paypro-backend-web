package air.found.payprowebbackend.business_logic;

import air.found.payprowebbackend.core.ApiError;
import air.found.payprowebbackend.core.ServiceResult;
import air.found.payprowebbackend.core.models.Merchant;
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

    public ServiceResult<Terminal> getTerminal(int tid) {
        try {
            Terminal terminal = air.found.payprowebbackend.data_access.manual.TerminalRepository.getTerminal(tid);
            if(terminal == null) {
                return ServiceResult.failure(ApiError.ERR_INVALID_TERMINAL);
            }
            return ServiceResult.success(terminal);
        }
        catch (Exception ex) {
            return ServiceResult.failure(ApiError.ERR_INVALID_REQUEST);
        }
    }

    public ServiceResult<List<Terminal>> getAllTerminalsForMerchant(Integer merchantId) {
        try {
            List<Terminal> terminals = air.found.payprowebbackend.data_access.manual.TerminalRepository.getAllTerminalsForMerchant(merchantId);
            return ServiceResult.success(terminals);
        }
        catch (Exception ex) {
            return ServiceResult.failure(ApiError.ERR_INVALID_REQUEST);
        }
    }
}
