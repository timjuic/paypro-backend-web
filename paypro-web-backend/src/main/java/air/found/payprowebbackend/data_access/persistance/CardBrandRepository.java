package air.found.payprowebbackend.data_access.persistance;

import air.found.payprowebbackend.core.models.CardBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardBrandRepository extends JpaRepository<CardBrand, Integer> {}
