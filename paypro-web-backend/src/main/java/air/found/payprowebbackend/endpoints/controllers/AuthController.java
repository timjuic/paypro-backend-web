package air.found.payprowebbackend.endpoints.controllers;

import air.found.payprowebbackend.business_logic.JwtService;
import air.found.payprowebbackend.business_logic.UserService;
import air.found.payprowebbackend.core.ApiError;
import air.found.payprowebbackend.core.ServiceResult;
import air.found.payprowebbackend.core.models.JwtTokenInfo;
import air.found.payprowebbackend.core.models.RefreshTokenRequest;
import air.found.payprowebbackend.core.models.UserAccount;
import air.found.payprowebbackend.core.network.ApiResponseBuilder;
import air.found.payprowebbackend.core.network.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<ResponseBody<JwtTokenInfo>> login(@RequestBody UserAccount userAccount) {
        ServiceResult<UserAccount> result = userService.loginUser(userAccount);
        if (result.isSuccess()) {
            JwtTokenInfo token = jwtService.getJwtToken(result.getData());
            return ApiResponseBuilder.buildSuccessResponse(token, "You have been successfully logged in!");
        }
        ApiError apiError = result.getApiError();
        return ApiResponseBuilder.buildErrorResponse(HttpStatus.BAD_REQUEST, apiError.getErrorMessage(), apiError.getErrorCode(), apiError.getErrorName());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseBody<JwtTokenInfo>> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtTokenInfo token = jwtService.refreshJwtToken(refreshTokenRequest);
        if(token != null) {
            return ApiResponseBuilder.buildSuccessResponse(token, "Your token has been successfully refreshed.");
        }
        ApiError apiError = ApiError.ERR_INVALID_OR_EXPIRED_REFRESH_TOKEN;
        return ApiResponseBuilder.buildErrorResponse(HttpStatus.BAD_REQUEST, apiError.getErrorMessage(), apiError.getErrorCode(), apiError.getErrorName());
    }

    private <T> ResponseEntity<ResponseBody<T>> respond(ServiceResult<T> result, String successMessage) {
        if (result.isSuccess()) {
            return ApiResponseBuilder.buildSuccessResponse(result.getData(), successMessage);
        } else {
            ApiError apiError = result.getApiError();
            return ApiResponseBuilder.buildErrorResponse(HttpStatus.BAD_REQUEST, apiError.getErrorMessage(), apiError.getErrorCode(), apiError.getErrorName());
        }
    }
}
