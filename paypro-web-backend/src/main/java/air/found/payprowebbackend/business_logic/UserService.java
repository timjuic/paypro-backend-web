package air.found.payprowebbackend.business_logic;

import air.found.payprowebbackend.core.ApiError;
import air.found.payprowebbackend.core.ServiceResult;
import air.found.payprowebbackend.core.models.Merchant;
import air.found.payprowebbackend.core.models.UserAccount;
import air.found.payprowebbackend.data_access.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ServiceResult<List<UserAccount>> getAllUsers() {
        try {
            List<UserAccount> userAccounts = userRepository.findAll();
            for (UserAccount account : userAccounts) {
                account.setPassword("*****");
            }
            return ServiceResult.success(userAccounts);
        }
        catch (Exception ex) {
            return ServiceResult.failure(ApiError.ERR_INVALID_REQUEST);
        }
    }
}
