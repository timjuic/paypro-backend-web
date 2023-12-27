package air.found.payprowebbackend.endpoints.controllers;

import air.found.payprowebbackend.business_logic.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/merchant")
public class MerchantController {
    private final MerchantService merchantService;
}
