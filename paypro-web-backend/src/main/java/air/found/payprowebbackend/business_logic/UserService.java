package air.found.payprowebbackend.business_logic;

import air.found.payprowebbackend.core.ApiError;
import air.found.payprowebbackend.core.ServiceResult;
import air.found.payprowebbackend.core.models.Merchant;
import air.found.payprowebbackend.core.models.UserAccount;
import air.found.payprowebbackend.data_access.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
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

    public ServiceResult<UserAccount> getUser(int id) {
        try {
            Optional<UserAccount> userAccount = userRepository.findById(id);
            if(userAccount.isEmpty()) {
                return ServiceResult.failure(ApiError.ERR_INVALID_USER);
            }
            UserAccount account = userAccount.get();
            account.setPassword("*****");
            return ServiceResult.success(account);
        }
        catch (Exception ex) {
            return ServiceResult.failure(ApiError.ERR_INVALID_REQUEST);
        }
    }

    public ServiceResult<UserAccount> loginUser(UserAccount userAccount) {
        if (isInvalidEmailFormat(userAccount.getEmailAddress())) {
            return ServiceResult.failure(ApiError.ERR_INVALID_EMAIL_FORMAT);
        }

        Optional<UserAccount> var = userRepository.findByEmailAddressAndPassword(userAccount.getEmailAddress(), userAccount.getPassword());

        if(var.isEmpty()) {
            return ServiceResult.failure(ApiError.ERR_INVALID_CREDENTIALS);
        }

        return userRepository.findByEmailAddress(userAccount.getEmailAddress())
                .map(ServiceResult::success)
                .orElse(ServiceResult.failure(ApiError.ERR_INVALID_CREDENTIALS));
    }

    private boolean isInvalidEmailFormat(String email) {
        return email == null || !EMAIL_PATTERN.matcher(email).matches();
    }
}
