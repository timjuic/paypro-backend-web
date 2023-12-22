package air.found.payprowebbackend.core.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_name", length = 20)
    private String statusName;

    @OneToMany(mappedBy = "status")
    private List<Merchant> merchants;

    @OneToMany(mappedBy = "status")
    private List<Terminal> terminals;

    public Status(int id, String name) {
        statusId = id;
        statusName = name;
    }
}
