package air.found.payprowebbackend.core.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user_accounts")
@Data
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email_address", nullable = false, length = 40)
    private String emailAddress;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed = false;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "forgot_password_code", length = 6)
    private String forgotPasswordCode;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "isGoogle")
    private Boolean isGoogle = false;

    @ManyToMany
    @JoinTable(
            name = "user_merchants",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "merchant_id")
    )
    private Set<Merchant> merchants;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
