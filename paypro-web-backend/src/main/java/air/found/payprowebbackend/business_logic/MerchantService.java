package air.found.payprowebbackend.business_logic;

import air.found.payprowebbackend.core.ServiceResult;
import air.found.payprowebbackend.core.models.Merchant;
import air.found.payprowebbackend.data_access.persistance.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantsRepository;

    public ServiceResult<List<Merchant>> getAllMerchants() {
        return null;
    }
}
