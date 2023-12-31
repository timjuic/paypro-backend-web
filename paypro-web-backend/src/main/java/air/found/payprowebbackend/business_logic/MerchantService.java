package air.found.payprowebbackend.business_logic;

import air.found.payprowebbackend.core.ApiError;
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
        try {
            List<Merchant> merchants = air.found.payprowebbackend.data_access.manual.MerchantRepository.getAllMerchants();
            return ServiceResult.success(merchants);
        }
        catch (Exception ex) {
            return ServiceResult.failure(ApiError.ERR_INVALID_REQUEST);
        }
    }

    public ServiceResult<Merchant> getMerchant(int mid) {
        try {
            Merchant merchant = air.found.payprowebbackend.data_access.manual.MerchantRepository.getMerchant(mid);
            if(merchant == null) {
                return ServiceResult.failure(ApiError.ERR_INVALID_MERCHANT);
            }
            return ServiceResult.success(merchant);
        }
        catch (Exception ex) {
            return ServiceResult.failure(ApiError.ERR_INVALID_REQUEST);
        }
    }
}
