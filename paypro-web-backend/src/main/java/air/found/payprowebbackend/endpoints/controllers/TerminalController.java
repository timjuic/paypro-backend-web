package air.found.payprowebbackend.endpoints.controllers;

import air.found.payprowebbackend.business_logic.TerminalService;
import air.found.payprowebbackend.core.ApiError;
import air.found.payprowebbackend.core.ServiceResult;
import air.found.payprowebbackend.core.models.Terminal;
import air.found.payprowebbackend.core.network.ApiResponseBuilder;
import air.found.payprowebbackend.core.network.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/terminal")
public class TerminalController {
    private final TerminalService terminalService;

    @GetMapping
    public ResponseEntity<ResponseBody<List<Terminal>>> getAllTerminals() {
        ServiceResult<List<Terminal>> result = terminalService.getAllTerminals();
        return respond(result, "Terminals successfully fetched");
    }

    @GetMapping("/{mid}")
    public ResponseEntity<ResponseBody<List<Terminal>>> getAllTerminalsForMerchant(@PathVariable("mid") Integer merchantId) {
        ServiceResult<List<Terminal>> result = terminalService.getAllTerminalsForMerchant(merchantId);
        return respond(result, "Terminals for merchant successfully fetched");
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
