package templates.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private double amount;
    private LocalDateTime timestamp;

    // Constructors, getters, setters
}
