package air.found.payprowebbackend.core.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card_brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_brand_id")
    private Integer cardBrandId;

    @Column(name = "name", length = 100)
    private String name;
}