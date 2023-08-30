package templates.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "customer")
    private Set<PaymentTransaction> paymentTransactions;

    // Constructors, getters, setters
}
