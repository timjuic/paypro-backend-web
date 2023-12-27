package air.found.payprowebbackend.endpoints.controllers;

import air.found.payprowebbackend.business_logic.MerchantService;
import air.found.payprowebbackend.core.ApiError;
import air.found.payprowebbackend.core.ServiceResult;
import air.found.payprowebbackend.core.models.Merchant;
import air.found.payprowebbackend.core.network.ApiResponseBuilder;
import air.found.payprowebbackend.core.network.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/merchant")
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping
    public ResponseEntity<ResponseBody<List<Merchant>>> getAllMerchants() {
        ServiceResult<List<Merchant>> result = merchantService.getAllMerchants();
        return respond(result, "Merchants fetched successfully");
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
