package air.found.payprowebbackend;

import air.found.payprowebbackend.core.enums.CardBrandType;
import air.found.payprowebbackend.core.enums.StatusType;
import air.found.payprowebbackend.core.models.CardBrand;
import air.found.payprowebbackend.core.models.Status;
import air.found.payprowebbackend.data_access.persistance.CardBrandRepository;
import air.found.payprowebbackend.data_access.persistance.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private CardBrandRepository cardBrandRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void run(String... args) throws Exception {
        for(CardBrandType brand : CardBrandType.values()) {
            CardBrand cardBrand = new CardBrand();
            cardBrand.setCardBrandId(brand.getId());
            cardBrand.setName(brand.getName());
            cardBrandRepository.save(cardBrand);
        }

        for(StatusType statusType : StatusType.values()) {
            Status status = new Status();
            status.setStatusId(statusType.getId());
            status.setStatusName(statusType.getName());
            statusRepository.save(status);
        }
    }
}
