package air.found.payprowebbackend.business_logic;

import air.found.payprowebbackend.core.ServiceResult;
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
        return null;
    }
}
